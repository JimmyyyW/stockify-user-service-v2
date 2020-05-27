package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fasterxml.jackson.annotation.JsonProperty
import com.fdm0506.stocky.userservicev2.adaptor.persistence.RxUserRepository
import com.fdm0506.stocky.userservicev2.application.service.EmailBuddyService
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

private val logger = KotlinLogging.logger{  }

@RestController
class UpdateUserRestController(val userRepository: RxUserRepository,
                               val emailBuddyService: EmailBuddyService) {

    @PutMapping(value = ["/api/v2/user/update"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateUserInfo(@RequestBody resource: NewUserInfoResource): Mono<String> {
        return userRepository.findByUsername(resource.username)
                .map {
                    if (resource.email != it.email) {
                        it.email = resource.email
                        it.enabled = false
                        emailBuddyService.sendActivationEmail(resource.email, it._id)
                        logger.info { "sent activation email for: ${it.username} to: ${resource.email}" }
                    }
                    if (resource.password != "******") {
                        it.password = resource.password
                    }
                    it.name = resource.name
                    it.surname = resource.surname
                    userRepository.save(it).subscribe()
                }.map { "{\"outcome\":\"success\"}" }
                .doOnError { logger.warn { "error updating user" } }
                .onErrorMap { t ->
                    emailBuddyService.sendErrorEmail(t.localizedMessage)
                    Exception(t.message)
                }

    }
}

data class  NewUserInfoResource(
        @JsonProperty
        val username: String,

        @JsonProperty
        val name: String,

        @JsonProperty
        val surname: String,

        @JsonProperty
        val password: String,

        @JsonProperty
        val email: String
)