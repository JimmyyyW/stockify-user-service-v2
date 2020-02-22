package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.Exception.PersistUserException
import com.fdm0506.stocky.userservicev2.application.port.out.DeleteUserPort
import com.fdm0506.stocky.userservicev2.domain.model.User
import com.fdm0506.stocky.userservicev2.domain.response.DeleteUserResponse
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class DeleteUserPersistenceAdaptor(private val rxUserRepository: RxUserRepository): DeleteUserPort {
    override fun deleteUser(_id: Mono<ObjectId>): Mono<DeleteUserResponse> {
        return try {
            rxUserRepository.deleteById(_id.block().toString())
            Mono.just(DeleteUserResponse(outcome = "success", _id = _id.block()))
        } catch (e: PersistUserException) {
            //TODO: log here
            println("failed to delete user from database")
            Mono.just(DeleteUserResponse(outcome = "failure", _id = _id.block()))
        }
    }
}