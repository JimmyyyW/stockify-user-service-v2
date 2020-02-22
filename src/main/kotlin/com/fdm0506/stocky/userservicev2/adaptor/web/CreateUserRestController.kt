package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase
import com.fdm0506.stocky.userservicev2.domain.response.CreateUserResponse
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@Validated
class CreateUserRestController(val createUserUseCase: CreateUserUseCase) {

    @PostMapping(value = ["v2/user/create"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun createNewUser(@RequestBody @Valid @Validated createUserResource: CreateUserResource) : Mono<CreateUserResponse> {
        return createUserUseCase.createUser(createUserResource.toCommand())
    }
}