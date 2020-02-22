package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase
import com.fdm0506.stocky.userservicev2.application.service.EmailBuddyService
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
class CreateUserRestController(val createUserUseCase: CreateUserUseCase,
                               val emailBuddyService: EmailBuddyService) {

    @PostMapping(value = ["v2/user/create"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun createNewUser(@RequestBody @Valid @Validated createUserResource: CreateUserResource) : Mono<CreateUserResponse> {
        val createUserResponse: Mono<CreateUserResponse> = createUserUseCase.createUser(createUserResource.toCommand())
        val outcome: String = createUserResponse.map { x -> x.outcome }.toString()
        println(outcome)
        if (outcome == "success") emailBuddyService.sendActivationEmail(createUserResource.email)
        emailBuddyService.sendActivationEmail(createUserResource.email)
        return createUserResponse
    }
}