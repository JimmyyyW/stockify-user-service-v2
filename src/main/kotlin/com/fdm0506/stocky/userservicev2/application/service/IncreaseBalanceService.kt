package com.fdm0506.stocky.userservicev2.application.service

import com.fdm0506.stocky.userservicev2.application.port.`in`.IncreaseBalanceUseCase
import com.fdm0506.stocky.userservicev2.application.port.out.ChangeBalancePort
import com.fdm0506.stocky.userservicev2.domain.response.ChangeBalanceResponse
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
@RequiredArgsConstructor
class IncreaseBalanceService(val changeBalancePort: ChangeBalancePort): IncreaseBalanceUseCase {

    override fun increaseBalance(command: IncreaseBalanceUseCase.IncreaseBalanceCommand): Mono<ChangeBalanceResponse> {
        return changeBalancePort.increaseBalance(command.user)
    }
}