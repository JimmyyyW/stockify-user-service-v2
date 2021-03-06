package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.adaptor.web.ChangeBalanceRequestResourceNoSession
import com.fdm0506.stocky.userservicev2.adaptor.web.ChangeBalanceRequestResourceWithSession
import com.fdm0506.stocky.userservicev2.application.port.out.ChangeBalancePort
import com.fdm0506.stocky.userservicev2.domain.model.User
import com.fdm0506.stocky.userservicev2.domain.response.ChangeBalanceResponse
import org.bson.types.Decimal128
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.security.Principal

@Component
class UpdateUserPersistenceAdaptor(val rxUserRepository: RxUserRepository): ChangeBalancePort {


    override fun reduceBalance(changeRequest: Mono<ChangeBalanceRequestResourceWithSession>, principal: Mono<Principal>): Mono<ChangeBalanceResponse> {
        val changeAmount: Mono<Decimal128> = changeRequest.map { it.transactionAmount }

        val userMono: Mono<User> =
                principal
                        .map { p -> p.name }
                        .flatMap { username -> rxUserRepository.findByEmail(username) }
                        .switchIfEmpty(Mono.error(UsernameNotFoundException("username not in database")))

        return changeBalance(userMono, changeAmount)
    }

    override fun increaseBalance(changeRequestNoSession: Mono<ChangeBalanceRequestResourceNoSession>): Mono<ChangeBalanceResponse> {
        val changeAmount: Mono<Decimal128> = changeRequestNoSession.map { it.transactionAmount }
        val user: Mono<User> = changeRequestNoSession.flatMap { rxUserRepository.findByUsername(it.username) }
        return changeBalance(user, changeAmount)
    }

    override fun checkBalance(): Mono<ChangeBalanceResponse> {
        return Mono.empty()
    }

    fun changeBalance(user : Mono<User>, amount: Mono<Decimal128>): Mono<ChangeBalanceResponse> {
        return Mono.zip(amount, user).flatMap {
            val change = it.t1
            val targetUser: User = it.t2
            val updatedUser = sumBalance(targetUser, change)
            rxUserRepository.save(updatedUser)
                    .map { ChangeBalanceResponse("success", updatedUser.balance!!) }
        }
    }

    fun sumBalance(user: User, num2: Decimal128): User {
        user.balance = Decimal128(user.balance!!.bigDecimalValue() + num2.bigDecimalValue())
        return user
    }

}