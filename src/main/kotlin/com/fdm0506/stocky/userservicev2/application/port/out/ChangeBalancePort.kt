package com.fdm0506.stocky.userservicev2.application.port.out

import com.fdm0506.stocky.userservicev2.domain.response.ChangeBalanceResponse
import org.bson.types.Decimal128
import org.springframework.stereotype.Component

@Component
interface ChangeBalancePort {
    fun reduceBalance(): ChangeBalanceResponse

    fun increaseBalance(): ChangeBalanceResponse

    fun checkBalance(): ChangeBalanceResponse
}