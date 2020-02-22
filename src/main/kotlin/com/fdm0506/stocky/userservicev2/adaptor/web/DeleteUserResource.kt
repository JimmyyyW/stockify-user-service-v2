package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fdm0506.stocky.userservicev2.application.port.`in`.DeleteUserUseCase
import lombok.Data
import lombok.RequiredArgsConstructor
import org.bson.types.ObjectId
import reactor.core.publisher.Mono
import javax.validation.constraints.NotNull

@Data
@RequiredArgsConstructor
class DeleteUserResource {
    fun toCommand(_id: ObjectId): DeleteUserUseCase.DeleteUserCommand {
        return DeleteUserUseCase.DeleteUserCommand(Mono.just(_id))
    }
}