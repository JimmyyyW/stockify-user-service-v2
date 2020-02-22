package com.fdm0506.stocky.userservicev2.application.service

import com.fdm0506.stocky.userservicev2.Exception.GeneralPortException
import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase
import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase.CreateUserCommand
import com.fdm0506.stocky.userservicev2.application.port.out.SaveUserPort
import com.fdm0506.stocky.userservicev2.domain.response.CreateUserResponse
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
@RequiredArgsConstructor
class CreateUserService(private val saveUserPort: SaveUserPort) : CreateUserUseCase {

    override fun createUser(command: CreateUserCommand) : Mono<CreateUserResponse> {
        try {
            saveUserPort.saveNewUser(command.user)
        } catch (e: GeneralPortException) {
            return Mono.just(CreateUserResponse("failure", command.user.block()))
        }
        return Mono.just(CreateUserResponse("success", command.user.block()))
    }
}