package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.Exception.PersistUserException
import com.fdm0506.stocky.userservicev2.application.port.out.DeleteUserPort
import com.fdm0506.stocky.userservicev2.domain.model.User
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
    override fun deleteUser(_id: Mono<ObjectId>): Mono<DeleteUserResponse> {
        return  _id.flatMap { rxUserRepository.deleteBy_id(it) }
                .doOnSuccess{ logger.info { "removed user $it" } }
                .doOnError { logger.info { "error removing user $it" } }
                .subscribeOn(Schedulers.elastic())
    }

    override fun deleteAllUserByUsername(username: Mono<String>): Mono<DeleteAllUserByUsernameResponse> {
        return username.flatMap { rxUserRepository.deleteAllByUsername(it) }
                .doOnSuccess{ logger.info { "successfully deleted user $it" } }
                .map { DeleteAllUserByUsernameResponse("success", "user gone") }
    }
}


//    override fun deleteUser(_id: Mono<ObjectId>): Mono<DeleteUserResponse> {
//        return try {
//            val idString: String = _id.block().toString()
//            var outcome = "failure"
//            if (userRepository.findById(idString).isPresent) {
//                userRepository.deleteById(idString)
//                outcome = "success"
//            }
//            Mono.just(DeleteUserResponse(outcome = outcome, _id = idString))
//            //todo: log here
//
//        } catch (e: PersistUserException) {
//            //todo: log here
//            println("failed to delete user from database")
//            Mono.just(DeleteUserResponse(outcome = "failure", _id = _id.block().toString()))
//        }
//    }