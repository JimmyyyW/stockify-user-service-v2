package com.fdm0506.stocky.userservicev2.application.port.out

import com.fdm0506.stocky.userservicev2.adaptor.web.ActivateUserRequestResource
import com.fdm0506.stocky.userservicev2.domain.response.ActivationRequestResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
interface ActivateUserPort {
    fun activateUser(activationRequest: Mono<ActivateUserRequestResource>): Mono<ActivationRequestResponse>
}