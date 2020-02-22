package com.fdm0506.stocky.userservicev2.application.port.out

import com.fdm0506.stocky.userservicev2.domain.model.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
interface FindUserPort {
    fun findAllUsers(): Flux<User>

    fun findUserById()

    fun findUserByNameRegex(regex: String?): Flux<User>
}