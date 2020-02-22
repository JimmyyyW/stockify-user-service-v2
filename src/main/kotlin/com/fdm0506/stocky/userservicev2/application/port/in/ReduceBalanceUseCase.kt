package com.fdm0506.stocky.userservicev2.application.port.`in`

import com.fdm0506.stocky.userservicev2.application.port.common.SelfValidating
import lombok.Getter

interface ReduceBalanceUseCase {

    fun reduceBalance(command : ReduceBalanceCommand)

    @Getter
    class ReduceBalanceCommand : SelfValidating<ReduceBalanceCommand>()

}