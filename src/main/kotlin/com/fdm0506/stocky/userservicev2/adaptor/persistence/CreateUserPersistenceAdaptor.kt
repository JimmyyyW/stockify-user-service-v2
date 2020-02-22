package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.Exception.PersistUserException
import com.fdm0506.stocky.userservicev2.application.port.out.SaveUserPort
import com.fdm0506.stocky.userservicev2.domain.model.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CreateUserPersistenceAdaptor(val userRepository: UserRepository) : SaveUserPort {

    override fun saveNewUser(user: Mono<User>) {
        try {
            user.block()?.let { userRepository.save(it) }
        } catch (e: PersistUserException) {
            println(e.message)
        }
    }
}