package com.fdm0506.stocky.userservicev2.application.port.out

import com.fdm0506.stocky.userservicev2.adaptor.web.ChangeBalanceRequestResourceNoSession
import com.fdm0506.stocky.userservicev2.adaptor.web.ChangeBalanceRequestResourceWithSession
import com.fdm0506.stocky.userservicev2.domain.response.ChangeBalanceResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.security.Principal

@Component
interface ChangeBalancePort {
    fun reduceBalance(changeRequest: Mono<ChangeBalanceRequestResourceWithSession>, principal: Mono<Principal>): Mono<ChangeBalanceResponse>

    fun increaseBalance(changeRequestNoSession: Mono<ChangeBalanceRequestResourceNoSession>): Mono<ChangeBalanceResponse>

    fun checkBalance(): Mono<ChangeBalanceResponse>
}