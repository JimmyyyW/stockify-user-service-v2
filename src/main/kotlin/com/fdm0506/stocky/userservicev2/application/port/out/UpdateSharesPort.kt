package com.fdm0506.stocky.userservicev2.application.port.out

import com.fdm0506.stocky.userservicev2.adaptor.web.UpdateSharesRequestResource
import com.fdm0506.stocky.userservicev2.domain.model.TransactionType
import com.fdm0506.stocky.userservicev2.domain.response.UpdateSharesResponse
import org.bson.types.Decimal128
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
interface UpdateSharesPort {

    fun updateShares(username: String, type: TransactionType, stockSymbol: String, volume: Int): Mono<UpdateSharesResponse>
}