package com.fdm0506.stocky.userservicev2.application.service

import com.fdm0506.stocky.userservicev2.application.port.`in`.DeleteUserUseCase
import com.fdm0506.stocky.userservicev2.application.port.out.DeleteUserPort
import com.fdm0506.stocky.userservicev2.domain.response.DeleteAllUserByUsernameResponse
import com.fdm0506.stocky.userservicev2.domain.response.DeleteUserResponse
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
@AllArgsConstructor
class DeleteUserService(val deleteUserPort: DeleteUserPort) : DeleteUserUseCase {

    override fun deleteUser(command: DeleteUserUseCase.DeleteUserCommand): Mono<DeleteUserResponse> {
        return deleteUserPort.deleteUserRx(command.user)
    }

    override fun deleteUserByUsername(command: DeleteUserUseCase.DeleteUserByUsernameCommand): Mono<DeleteAllUserByUsernameResponse> {
        return deleteUserPort.deleteAllUserByUsername(command.username)
    }
}