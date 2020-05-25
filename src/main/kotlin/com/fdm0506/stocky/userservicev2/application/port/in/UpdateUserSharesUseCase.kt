package com.fdm0506.stocky.userservicev2.application.port.`in`

import com.fasterxml.jackson.annotation.JsonCreator
import com.fdm0506.stocky.userservicev2.adaptor.web.UpdateSharesRequestResource
import com.fdm0506.stocky.userservicev2.application.port.common.SelfValidating
import com.fdm0506.stocky.userservicev2.domain.response.UpdateSharesResponse
import lombok.Getter
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import javax.validation.constraints.NotEmpty

@Component
interface UpdateUserSharesUseCase {

    fun updateHeldShares(command : UpdateSharesCommand): Mono<UpdateSharesResponse> //returns new shares

    @Getter
    class UpdateSharesCommand @JsonCreator constructor(@field:NotEmpty val tradeRequest: Mono<UpdateSharesRequestResource>) : SelfValidating<UpdateUserSharesUseCase>()
}