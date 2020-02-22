package com.fdm0506.stocky.userservicev2.application.port.out

import com.fdm0506.stocky.userservicev2.domain.model.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
interface SaveUserPort {
    fun saveNewUser(user: Mono<User>)
}