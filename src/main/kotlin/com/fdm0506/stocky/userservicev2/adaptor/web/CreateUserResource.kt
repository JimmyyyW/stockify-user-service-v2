package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase
import com.fdm0506.stocky.userservicev2.domain.model.User
import lombok.Data
import lombok.RequiredArgsConstructor
import org.bson.types.ObjectId
import reactor.core.publisher.Mono
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Data
@RequiredArgsConstructor
data class CreateUserResource(@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z]+){6,16}\$")
                         @get:NotBlank
                         @JsonProperty("name")
                         @JsonPropertyDescription("name of account user")
                         val name: String,

                         @get:Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+){6,16}\$")
                         @get:NotBlank
                         @JsonProperty("username")
                         @JsonPropertyDescription("alias/on screen name for account")
                         val username: String,

                         @get:Pattern(regexp = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#\$%!?]).{8,40})")
                         @get:NotBlank
                         @JsonProperty("password")
                         @JsonPropertyDescription("password, must be between 8 and 40 chars, containing a capital and @#\\$%!?")
                         val password: String,

                         @get:Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\ x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
                         @get:NotBlank
                         @JsonProperty("email")
                         @JsonPropertyDescription("email address, must comply to standard email format")
                         val email: String) {

    fun toCommand(): CreateUserUseCase.CreateUserCommand {
        return CreateUserUseCase.CreateUserCommand(
                Mono.just(User(ObjectId(), this.name, this.username, this.password, this.email)))
    }
}