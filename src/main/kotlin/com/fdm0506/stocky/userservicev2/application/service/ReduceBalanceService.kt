package com.fdm0506.stocky.userservicev2.application.service

import com.fdm0506.stocky.userservicev2.application.port.`in`.ReduceBalanceUseCase
import com.fdm0506.stocky.userservicev2.application.port.out.ChangeBalancePort
import com.fdm0506.stocky.userservicev2.domain.response.ChangeBalanceResponse
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.security.Principal

@Service
@RequiredArgsConstructor
class ReduceBalanceService(val changeBalancePort: ChangeBalancePort): ReduceBalanceUseCase {
    override fun reduceBalance(command: ReduceBalanceUseCase.ReduceBalanceCommand): Mono<ChangeBalanceResponse> {
        return Mono.empty()
    }

    override fun reduceBalanceB(command: ReduceBalanceUseCase.ReduceBalanceCommandB, principal: Mono<Principal>): Mono<ChangeBalanceResponse> {
        return changeBalancePort.reduceBalance(command.value, principal)
    }
}