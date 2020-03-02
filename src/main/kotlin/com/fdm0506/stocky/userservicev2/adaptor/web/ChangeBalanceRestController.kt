package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.IncreaseBalanceUseCase
import com.fdm0506.stocky.userservicev2.application.port.`in`.ReduceBalanceUseCase
import com.fdm0506.stocky.userservicev2.domain.response.ChangeBalanceResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

//TODO: add session - only
@RestController
@Validated
class ChangeBalanceRestController (val reduceBalanceUseCase: ReduceBalanceUseCase,
                                   val increaseBalanceUseCase: IncreaseBalanceUseCase) {

    @PutMapping(value = ["/v2/user/balance/reduce"])
    fun decreaseBalance(@RequestBody @Valid changeBalanceRequestResource: ChangeBalanceRequestResource)
            : Mono<ChangeBalanceResponse> {
        //load user into context

        return reduceBalanceUseCase.reduceBalance(changeBalanceRequestResource.toReduceCommand())
    }

    @PutMapping(value = ["/v2/user/balance/increase"])
    fun increaseBalace(@RequestBody @Valid changeBalanceRequestResource: ChangeBalanceRequestResource)
            : Mono<ChangeBalanceResponse> {
        return increaseBalanceUseCase.increaseBalance(changeBalanceRequestResource.toIncreaseCommand())
    }
}