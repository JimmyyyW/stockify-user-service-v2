package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.FindAllUserUseCase
import com.fdm0506.stocky.userservicev2.domain.model.User
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class FindUserRestController(val findAllUserUseCase: FindAllUserUseCase) {

    @GetMapping(value = ["v2/user/all"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllUsers(@RequestBody findUserResource: FindAllUserResource) : Flux<User> {
        return findAllUserUseCase.findAllUsers(findUserResource.toCommand(null))
    }

    @GetMapping(value = ["v2/user/search/{regex}"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun findUsersBySearchRegex(@PathVariable regex: String, findUserResource: FindAllUserResource): Flux<User> {
        return findAllUserUseCase.findAllUsersBySearch(findUserResource.toCommand(regex))
    }
}