package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase
import com.fdm0506.stocky.userservicev2.domain.response.ActivationRequestResponse
import lombok.AllArgsConstructor
import lombok.Data
import reactor.core.publisher.Mono

@Data
@AllArgsConstructor
data class ActivateUserRequestResource(
        val userId: String
) {
   fun toActivationCommand(): CreateUserUseCase.ActivateUserCommand {
       return CreateUserUseCase.ActivateUserCommand(
               Mono.just(ActivateUserRequestResource(this.userId))
       )
   }
}