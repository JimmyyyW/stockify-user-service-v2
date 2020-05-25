package com.fdm0506.stocky.userservicev2.application.service

import com.fdm0506.stocky.userservicev2.application.port.`in`.UpdateUserSharesUseCase
import com.fdm0506.stocky.userservicev2.application.port.out.UpdateSharesPort
import com.fdm0506.stocky.userservicev2.domain.response.UpdateSharesResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserSharesService (private val updateSharesPort: UpdateSharesPort): UpdateUserSharesUseCase {

    override fun updateHeldShares(command: UpdateUserSharesUseCase.UpdateSharesCommand): Mono<UpdateSharesResponse> {
        return command.tradeRequest.flatMap {
            updateSharesPort.updateShares(it.username, it.type, it.stockSymbol, it.volume)
        }
    }
}