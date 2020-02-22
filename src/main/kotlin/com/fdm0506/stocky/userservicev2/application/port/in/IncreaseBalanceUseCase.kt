package com.fdm0506.stocky.userservicev2.application.port.`in`

import org.bson.types.Decimal128

interface IncreaseBalanceUseCase {

    fun increaseBalance(command : IncreaseBalanceCommand): Decimal128

    class IncreaseBalanceCommand
}