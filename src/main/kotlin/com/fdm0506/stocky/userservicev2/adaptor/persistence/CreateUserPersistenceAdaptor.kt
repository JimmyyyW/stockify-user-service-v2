package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.Exception.PersistUserException
import com.fdm0506.stocky.userservicev2.application.port.out.SaveUserPort
import com.fdm0506.stocky.userservicev2.domain.model.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CreateUserPersistenceAdaptor(val rxUserRepository: RxUserRepository) : SaveUserPort {

    override fun saveNewUser(user: Mono<User>) {
        try {
            user.flatMap { rxUserRepository.save(it) }
                    .subscribe() //TODO: Log here + FIND OUT WHY RX repo not working
        } catch (e: PersistUserException) {
            println(e.message) //TODO log here
        }
    }
}