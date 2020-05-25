package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.FindUserUseCase
import lombok.Data
import lombok.RequiredArgsConstructor

@Data
@RequiredArgsConstructor
class FindAllUserResource {
    fun toCommand(search: String?): FindUserUseCase.FindAllUserCommand {
        return FindUserUseCase.FindAllUserCommand(search)
    }
}