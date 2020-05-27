package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase
import com.fdm0506.stocky.userservicev2.application.service.EmailBuddyService
import com.fdm0506.stocky.userservicev2.domain.response.ActivationRequestResponse
import com.fdm0506.stocky.userservicev2.domain.response.CreateUserResponse
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import javax.validation.Valid

@RestController
@Validated
class CreateUserRestController(val createUserUseCase: CreateUserUseCase,
                               val emailBuddyService: EmailBuddyService) {

    @PostMapping(value = ["/register"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun registerUser(@RequestBody @Valid @Validated createUserResource: CreateUserResource) : Mono<CreateUserResponse> {
        val createUserResponseMono: Mono<CreateUserResponse>
        if (createUserUseCase.checkUsernameEmailAvailable(createUserResource.username, createUserResource.email)) {
            createUserResponseMono = createUserUseCase.createUser(createUserResource.toCommand())

            val activationString = createUserResponseMono.map { it.user!!._id }
            activationString
                    .subscribe { emailBuddyService.sendActivationEmail(createUserResource.email, it) }
        } else {
            createUserResponseMono = Mono.just(CreateUserResponse("failure", "username or email already exists", null))
        }
        return createUserResponseMono
                .onErrorMap { t ->
                    emailBuddyService.sendErrorEmail(t.localizedMessage)
                    Exception(t.message)
                }
    }

    @GetMapping(value = ["api/v2/user/activate/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun activateUser(@PathVariable id: String): Mono<ActivationRequestResponse> {
        return createUserUseCase.activateUser(ActivateUserRequestResource(id).toActivationCommand())
                .onErrorMap { t ->
                    emailBuddyService.sendErrorEmail(t.localizedMessage)
                    Exception(t.message)
                }
    }
}