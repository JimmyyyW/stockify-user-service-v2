package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.domain.model.User
import com.fdm0506.stocky.userservicev2.domain.response.DeleteAllUserByUsernameResponse
import com.fdm0506.stocky.userservicev2.domain.response.DeleteUserResponse
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun deleteAllByUsername(username: String)

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean
}

@Repository
interface RxUserRepository : ReactiveMongoRepository<User, String> {

    fun findByNameRegex(regex: String?): Flux<User>

    fun deleteBy_id(_id: ObjectId): Mono<DeleteUserResponse>

    fun findByUsername(username: String): Mono<User>

    fun findByEmail(username: String): Mono<User>

    fun deleteAllByUsername(username: String): Mono<DeleteAllUserByUsernameResponse>
}

