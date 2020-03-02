package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.application.port.out.FindUserPort
import com.fdm0506.stocky.userservicev2.domain.model.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class FindUserPersistenceAdaptor(val rxUserRepository: RxUserRepository,
                                 val userRepository: UserRepository): FindUserPort {

    override fun findAllUsers(): Flux<User> {
        return rxUserRepository.findAll()
    }

    override fun findUserById() {
        //TODO: impl
    }

    override fun existsByUsername(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    override fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun findUserByNameRegex(regex: String?): Flux<User> {
       return rxUserRepository.findByNameRegex(regex)
    }
}