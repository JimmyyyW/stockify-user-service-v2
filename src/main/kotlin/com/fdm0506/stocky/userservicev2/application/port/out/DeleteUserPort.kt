package com.fdm0506.stocky.userservicev2.application.port.out

import com.fdm0506.stocky.userservicev2.domain.model.User
import com.fdm0506.stocky.userservicev2.domain.response.DeleteAllUserByUsernameResponse
import com.fdm0506.stocky.userservicev2.domain.response.DeleteUserResponse
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
interface DeleteUserPort {

    fun deleteUser(_id : Mono<ObjectId>): Mono<DeleteUserResponse>

    fun deleteAllUserByUsername(username: Mono<String>): Mono<DeleteAllUserByUsernameResponse>
}