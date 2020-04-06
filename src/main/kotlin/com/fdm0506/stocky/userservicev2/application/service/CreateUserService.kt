package com.fdm0506.stocky.userservicev2.application.service

import com.fdm0506.stocky.userservicev2.adaptor.web.ActivateUserRequestResource
import com.fdm0506.stocky.userservicev2.exception.GeneralPortException
import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase
import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase.CreateUserCommand
import com.fdm0506.stocky.userservicev2.application.port.out.ActivateUserPort
import com.fdm0506.stocky.userservicev2.application.port.out.FindUserPort
import com.fdm0506.stocky.userservicev2.application.port.out.SaveUserPort
import com.fdm0506.stocky.userservicev2.domain.response.ActivationRequestResponse
import com.fdm0506.stocky.userservicev2.domain.response.CreateUserResponse
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
@RequiredArgsConstructor
class CreateUserService(private val saveUserPort: SaveUserPort,
                        private val findUserPort: FindUserPort,
                        private val activateUserPort: ActivateUserPort) : CreateUserUseCase {

    override fun createUser(command: CreateUserCommand): Mono<CreateUserResponse> {
        try {
            saveUserPort.saveNewUser(command.user) //todo: use reactive stream mapping
        } catch (e: GeneralPortException) {
            return Mono.just(CreateUserResponse("failure", "unable to persist user", command.user.block()!!))
        }
        return Mono.just(CreateUserResponse("success", "user created", command.user.block()!!))
    }

    override fun checkUsernameEmailAvailable(username: String, email: String): Boolean {
        return !(findUserPort.existsByEmail(email) || findUserPort.existsByEmail(username))
        //todo: check whether exception needs caught here... Maybe swap to returning a string for more accurate description
    }

    override fun activateUser(command: CreateUserUseCase.ActivateUserCommand): Mono<ActivationRequestResponse> {
        return activateUserPort.activateUser(command.activationRequest)
    }
}