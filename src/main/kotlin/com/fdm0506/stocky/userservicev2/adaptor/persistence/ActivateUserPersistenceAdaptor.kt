package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.adaptor.web.ActivateUserRequestResource
import com.fdm0506.stocky.userservicev2.application.port.out.ActivateUserPort
import com.fdm0506.stocky.userservicev2.domain.response.ActivationRequestResponse
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ActivateUserPersistenceAdaptor(val rxUserRepository: RxUserRepository) : ActivateUserPort {
    override fun activateUser(activationRequest: Mono<ActivateUserRequestResource>): Mono<ActivationRequestResponse> {
        return activationRequest
                .flatMap { rxUserRepository.findById(it.userId) }
                .map {
                    it.enabled = true
                    rxUserRepository.save(it).subscribe()
                }
                .map { ActivationRequestResponse("success", "successfully activated user") }
                .onErrorReturn( ActivationRequestResponse("failure", "unable to activate user"))
    }

}