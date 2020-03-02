package com.fdm0506.stocky.userservicev2.application.port.out

import com.fdm0506.stocky.userservicev2.domain.model.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
interface FindUserPort {
    fun findAllUsers(): Flux<User>

    fun findUserById()

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun findUserByNameRegex(regex: String?): Flux<User>
}