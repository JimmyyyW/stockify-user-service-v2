package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.FindUserUseCase
import com.fdm0506.stocky.userservicev2.domain.model.User
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class FindUserRestController(val findUserUseCase: FindUserUseCase) {

    @GetMapping(value = ["api/v2/user/all"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllUsers() : Flux<User> {
        val findUserResource = FindAllUserResource()
        return findUserUseCase.findAllUsers(findUserResource.toCommand(null))
    }

    @GetMapping(value = ["api/v2/user/search/{regex}"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun findUsersBySearchRegex(@PathVariable regex: String, findUserResource: FindAllUserResource): Flux<User> {
        return findUserUseCase.findAllUsersBySearch(findUserResource.toCommand(regex))
    }

    @GetMapping(value = ["api/v2/user/find/{username}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findUserByUsername(@PathVariable username: String) : Mono<User> {
        return findUserUseCase.findUserByUsername(username)
    }

    @GetMapping(value = ["api/v2/user/status/{username}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findUserByUsernameForStatus(@PathVariable username: String) : Mono<Boolean> {
        return findUserUseCase.findUserByUsername(username).map { it.enabled!! }
    }
}