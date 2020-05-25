package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.exception.PersistUserException
import com.fdm0506.stocky.userservicev2.application.port.out.SaveUserPort
import com.fdm0506.stocky.userservicev2.domain.model.User
import com.fdm0506.stocky.userservicev2.domain.model.UserShares
import mu.KotlinLogging
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

private val logger = KotlinLogging.logger { }

@Component
class CreateUserPersistenceAdaptor(val rxUserRepository: RxUserRepository,
                                   val sharesRepository: SharesRepository) : SaveUserPort {

    override fun saveNewUser(user: Mono<User>) {
        try {
            user.flatMap { rxUserRepository.save(it) }
                    .flatMap { sharesRepository.save(UserShares(ObjectId().toHexString(), it.username, HashMap())) }
                    .subscribe { logger.info("new user created: $it") } //TODO: Log here + FIND OUT WHY RX repo not working

        } catch (e: PersistUserException) {
            logger.warn { e.message } //TODO log here
        }
    }
}