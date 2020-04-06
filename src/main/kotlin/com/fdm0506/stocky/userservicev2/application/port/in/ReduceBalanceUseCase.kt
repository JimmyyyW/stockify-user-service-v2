package com.fdm0506.stocky.userservicev2.application.port.`in`

import com.fasterxml.jackson.annotation.JsonCreator
import com.fdm0506.stocky.userservicev2.adaptor.web.ChangeBalanceRequestResourceNoSession
import com.fdm0506.stocky.userservicev2.adaptor.web.ChangeBalanceRequestResourceWithSession
import com.fdm0506.stocky.userservicev2.application.port.common.SelfValidating
import com.fdm0506.stocky.userservicev2.domain.response.ChangeBalanceResponse
import lombok.Getter
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.security.Principal
import javax.validation.constraints.NotEmpty

@Component
interface ReduceBalanceUseCase {

    fun reduceBalance(command : ReduceBalanceCommand): Mono<ChangeBalanceResponse>
    fun reduceBalanceB(command : ReduceBalanceCommandB, principal: Mono<Principal>): Mono<ChangeBalanceResponse>

    @Getter
    class ReduceBalanceCommand @JsonCreator constructor(@field:NotEmpty val value: Mono<ChangeBalanceRequestResourceNoSession>): SelfValidating<ReduceBalanceCommand>()

    @Getter
    class ReduceBalanceCommandB @JsonCreator constructor(@field:NotEmpty val value: Mono<ChangeBalanceRequestResourceWithSession>): SelfValidating<ReduceBalanceCommandB>()
}