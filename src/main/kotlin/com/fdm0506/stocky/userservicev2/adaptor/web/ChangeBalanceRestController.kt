package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.IncreaseBalanceUseCase
import com.fdm0506.stocky.userservicev2.application.port.`in`.ReduceBalanceUseCase
import com.fdm0506.stocky.userservicev2.domain.response.ChangeBalanceResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.security.Principal
import javax.validation.Valid

//TODO: add session - only
@RestController
@Validated
class ChangeBalanceRestController (val reduceBalanceUseCase: ReduceBalanceUseCase,
                                   val increaseBalanceUseCase: IncreaseBalanceUseCase) {

    //TODO: changebalance by negative sum or decrease balance command??
    @PutMapping(value = ["api/v2/user/balance/reduce"])
    fun decreaseBalance(@RequestBody @Valid @Validated changeBalanceRequestResource: ChangeBalanceRequestResourceWithSession, principal: Mono<Principal>)
            : Mono<ChangeBalanceResponse> {
        //load user into context
        return reduceBalanceUseCase.reduceBalanceB(changeBalanceRequestResource.toReduceCommand(), principal)

    }

    @PutMapping(value = ["api/v2/user/balance/increase"])
    fun increaseBalance(@RequestBody @Valid @Validated changeBalanceRequestResourceNoSession: ChangeBalanceRequestResourceNoSession)
            : Mono<ChangeBalanceResponse> {
        return increaseBalanceUseCase.increaseBalance(changeBalanceRequestResourceNoSession.toIncreaseCommand())
    }
}