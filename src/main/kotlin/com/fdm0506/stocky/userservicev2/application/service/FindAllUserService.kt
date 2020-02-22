package com.fdm0506.stocky.userservicev2.application.service

import com.fdm0506.stocky.userservicev2.application.port.`in`.FindAllUserUseCase
import com.fdm0506.stocky.userservicev2.application.port.out.FindUserPort
import com.fdm0506.stocky.userservicev2.domain.model.User
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
@RequiredArgsConstructor
class FindAllUserService(private val findUserPort: FindUserPort): FindAllUserUseCase {

    override fun findAllUsers(command: FindAllUserUseCase.FindAllUserCommand): Flux<User> {
        return findUserPort.findAllUsers()
    }

    override fun findAllUsersBySearch(command: FindAllUserUseCase.FindAllUserCommand): Flux<User> {
        return findUserPort.findUserByNameRegex(command.search)
    }
}