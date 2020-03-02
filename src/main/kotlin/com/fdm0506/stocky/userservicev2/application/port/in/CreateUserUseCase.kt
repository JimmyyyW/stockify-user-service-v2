package com.fdm0506.stocky.userservicev2.application.port.`in`

import com.fasterxml.jackson.annotation.JsonCreator
import com.fdm0506.stocky.userservicev2.application.port.common.SelfValidating
import com.fdm0506.stocky.userservicev2.domain.response.CreateUserResponse
import com.fdm0506.stocky.userservicev2.domain.model.User
import lombok.Getter
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import javax.validation.constraints.NotEmpty

@Component
interface CreateUserUseCase {

    fun createUser(command: CreateUserCommand) : Mono<CreateUserResponse>

    fun checkUsernameEmailAvailable(username: String, email: String) : Boolean

    @Getter
    class CreateUserCommand @JsonCreator constructor(@field:NotEmpty val user: Mono<User>) : SelfValidating<CreateUserCommand>()

}

