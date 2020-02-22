package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.application.port.out.FindUserPort
import com.fdm0506.stocky.userservicev2.domain.model.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class FindUserPersistenceAdaptor(val rxUserRepository: RxUserRepository): FindUserPort {

    override fun findAllUsers(): Flux<User> {
        return rxUserRepository.findAll()
    }


    override fun findUserById() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findUserByNameRegex(regex: String?): Flux<User> {
       return rxUserRepository.findByNameRegex(regex)
    }
}