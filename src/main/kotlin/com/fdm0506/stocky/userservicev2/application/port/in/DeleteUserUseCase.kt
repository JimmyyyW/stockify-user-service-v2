package com.fdm0506.stocky.userservicev2.application.port.`in`

import com.fasterxml.jackson.annotation.JsonCreator
import com.fdm0506.stocky.userservicev2.application.port.common.SelfValidating
import com.fdm0506.stocky.userservicev2.domain.response.DeleteUserResponse
import lombok.Getter
import org.bson.types.ObjectId
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import javax.validation.constraints.NotEmpty

@Configuration
interface DeleteUserUseCase {

    fun deleteUser(command: DeleteUserCommand) : Mono<DeleteUserResponse>

    @Getter
    class DeleteUserCommand @JsonCreator constructor(@field:NotEmpty val user: Mono<ObjectId>) : SelfValidating<DeleteUserCommand>()
}