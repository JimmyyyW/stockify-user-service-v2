package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.domain.model.UserShares
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface SharesRepository : ReactiveMongoRepository<UserShares, String> {

    fun findByUser(username: String): Mono<UserShares>
}