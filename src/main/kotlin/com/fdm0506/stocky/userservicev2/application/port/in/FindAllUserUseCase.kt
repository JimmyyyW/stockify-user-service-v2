package com.fdm0506.stocky.userservicev2.application.port.`in`

import com.fdm0506.stocky.userservicev2.application.port.common.SelfValidating
import com.fdm0506.stocky.userservicev2.domain.model.User
import lombok.Getter
import reactor.core.publisher.Flux

interface FindAllUserUseCase {

    fun findAllUsers(command: FindAllUserCommand) : Flux<User>

    fun findAllUsersBySearch(command: FindAllUserCommand) : Flux<User>

    @Getter
    class FindAllUserCommand(val search: String?): SelfValidating<FindAllUserCommand>()
}