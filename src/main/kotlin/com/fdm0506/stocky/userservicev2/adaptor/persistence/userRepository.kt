package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.domain.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface UserRepository : MongoRepository<User, String> {
}

@Repository
interface RxUserRepository : ReactiveMongoRepository<User, String> {

    fun findByNameRegex(regex: String?): Flux<User>

}

