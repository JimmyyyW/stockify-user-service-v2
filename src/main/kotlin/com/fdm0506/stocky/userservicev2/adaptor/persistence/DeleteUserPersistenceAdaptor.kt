package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.application.port.out.DeleteUserPort
import com.fdm0506.stocky.userservicev2.domain.response.DeleteAllUserByUsernameResponse
import com.fdm0506.stocky.userservicev2.domain.response.DeleteUserResponse
import mu.KotlinLogging
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

private val logger = KotlinLogging.logger { }

@Component
class DeleteUserPersistenceAdaptor(private val rxUserRepository: RxUserRepository,
                                   private val userRepository: UserRepository) : DeleteUserPort {

    //todo: work out why this errors
    override fun deleteUser(_id: Mono<String>): Mono<DeleteUserResponse> {
        return  _id.flatMap { rxUserRepository.deleteBy_id(it) }
                .onErrorContinue { _, _ -> DeleteUserResponse("successs", "1") }
                .doOnSuccess{ logger.info { "removed user" } }
                .subscribeOn(Schedulers.elastic())
    }

    override fun deleteAllUserByUsername(username: Mono<String>): Mono<DeleteAllUserByUsernameResponse> {
        return username.flatMap { rxUserRepository.deleteAllByUsername(it) }
                .doOnSuccess{ logger.info { "successfully deleted user $it" } }
                .map { DeleteAllUserByUsernameResponse("success", "user gone") }
    }
}


