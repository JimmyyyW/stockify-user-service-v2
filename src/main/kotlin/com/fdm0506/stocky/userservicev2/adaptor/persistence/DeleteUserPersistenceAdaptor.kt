package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.Exception.PersistUserException
import com.fdm0506.stocky.userservicev2.application.port.out.DeleteUserPort
import com.fdm0506.stocky.userservicev2.domain.response.DeleteUserResponse
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class DeleteUserPersistenceAdaptor(private val rxUserRepository: RxUserRepository,
                                   private val userRepository: UserRepository): DeleteUserPort {
    override fun deleteUser(_id: Mono<ObjectId>): Mono<DeleteUserResponse> {
        return try {
            val idString: String = _id.block().toString()
            var outcome = "failure"
            if (userRepository.findById(idString).isPresent) {
                userRepository.deleteById(idString)
                outcome = "success"
            }
            Mono.just(DeleteUserResponse(outcome = outcome, _id = idString))

        } catch (e: PersistUserException) {
            println("failed to delete user from database")
            Mono.just(DeleteUserResponse(outcome = "failure", _id = _id.block().toString()))
        }
    }
}