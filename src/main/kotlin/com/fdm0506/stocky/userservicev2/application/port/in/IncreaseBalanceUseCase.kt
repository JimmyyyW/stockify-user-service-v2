package com.fdm0506.stocky.userservicev2.application.port.`in`

import com.fasterxml.jackson.annotation.JsonCreator
import com.fdm0506.stocky.userservicev2.adaptor.web.ChangeBalanceRequestResource
import com.fdm0506.stocky.userservicev2.application.port.common.SelfValidating
import com.fdm0506.stocky.userservicev2.domain.model.User
import com.fdm0506.stocky.userservicev2.domain.response.ChangeBalanceResponse
import lombok.Getter
import org.bson.types.Decimal128
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import javax.validation.constraints.NotEmpty

@Component
interface IncreaseBalanceUseCase {

    fun increaseBalance(command : IncreaseBalanceCommand): Mono<ChangeBalanceResponse> //returns new balance

    @Getter
    class IncreaseBalanceCommand @JsonCreator constructor(@field:NotEmpty val user: Mono<ChangeBalanceRequestResource>) : SelfValidating<IncreaseBalanceCommand>()
}