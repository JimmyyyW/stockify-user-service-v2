package com.fdm0506.stocky.userservicev2.adaptor.persistence

import com.fdm0506.stocky.userservicev2.application.port.out.ChangeBalancePort
import com.fdm0506.stocky.userservicev2.domain.response.ChangeBalanceResponse
import org.springframework.stereotype.Component

@Component
class UpdateUserPersistenceAdaptor(val rxUserRepository: RxUserRepository,
                                   val userRepository: UserRepository): ChangeBalancePort {

    override fun reduceBalance(): ChangeBalanceResponse {
        TODO("not implemented")
    }

    override fun increaseBalance(): ChangeBalanceResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkBalance(): ChangeBalanceResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}