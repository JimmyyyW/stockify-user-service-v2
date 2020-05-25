package com.fdm0506.stocky.userservicev2.application.service

import com.fdm0506.stocky.userservicev2.application.port.`in`.FindUserUseCase
import com.fdm0506.stocky.userservicev2.application.port.out.FindUserPort
import com.fdm0506.stocky.userservicev2.domain.model.User
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@RequiredArgsConstructor
class FindUserService(private val findUserPort: FindUserPort): FindUserUseCase {

    override fun findAllUsers(command: FindUserUseCase.FindAllUserCommand): Flux<User> {
        return findUserPort.findAllUsers()
    }

    override fun findAllUsersBySearch(command: FindUserUseCase.FindAllUserCommand): Flux<User> {
        return findUserPort.findUserByNameRegex(command.search)
    }

    override fun findUserByUsername(username: String): Mono<User> {
        return findUserPort.findUserByUsername(username)
    }
}