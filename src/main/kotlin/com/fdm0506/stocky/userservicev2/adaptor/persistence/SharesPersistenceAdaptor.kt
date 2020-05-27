package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.adaptor.web.UpdateSharesRequestResource
import com.fdm0506.stocky.userservicev2.application.port.out.UpdateSharesPort
import com.fdm0506.stocky.userservicev2.domain.model.TransactionType
import com.fdm0506.stocky.userservicev2.domain.response.UpdateSharesResponse
import com.fdm0506.stocky.userservicev2.exception.NotEnoughSharesException
import org.bson.types.Decimal128
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class SharesPersistenceAdaptor (val sharesRepository: SharesRepository): UpdateSharesPort {

    override fun updateShares(username: String, type: TransactionType, stockSymbol: String, volume: Int): Mono<UpdateSharesResponse> {
        return when (type) {
            TransactionType.SELL -> {
                sharesRepository.findByUser(username)
                        .flatMap {
                            if (it.shares[stockSymbol] != null) {
                                println(it)
                                val currentAmount =  it.shares[stockSymbol]
                                val newAmount = currentAmount!!.minus(volume)
                                if (newAmount.toDouble() >= 0) {
                                    it.shares[stockSymbol] = newAmount
                                    if (newAmount == 0) {
                                        it.shares.remove(stockSymbol)
                                    }
                                } else if (newAmount == 0) {
                                    it.shares.remove(stockSymbol)
                                }
                            }
                            sharesRepository.save(it)
                        }
                        .map { UpdateSharesResponse("success", it.shares) }
                        .onErrorMap { Exception("failed to add shares") }
            }
            TransactionType.BUY -> {
                sharesRepository.findByUser(username)
                        .flatMap {
                            println(it)
                            if (it.shares[stockSymbol] != null) {
                                val currentAmount = it.shares[stockSymbol]
                                val newAmount = currentAmount!! + volume
                                if (newAmount.toDouble() > 0) {
                                    it.shares[stockSymbol] = newAmount
                                }
                            } else {
                                it.shares[stockSymbol] = volume
                            }
                            sharesRepository.save(it)
                        }
                        .map { UpdateSharesResponse("success", it.shares) }
                        .onErrorMap { Exception("failed to remove shares") }
            }
        }
    }
}