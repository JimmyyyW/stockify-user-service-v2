package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase
import com.fdm0506.stocky.userservicev2.application.port.`in`.IncreaseBalanceUseCase
import com.fdm0506.stocky.userservicev2.application.port.`in`.ReduceBalanceUseCase
import com.fdm0506.stocky.userservicev2.bCryptPasswordEncoder
import com.fdm0506.stocky.userservicev2.domain.model.User
import lombok.AllArgsConstructor
import lombok.Data
import org.bson.types.Decimal128
import org.bson.types.ObjectId
import reactor.core.publisher.Mono
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Data
@AllArgsConstructor
data class ChangeBalanceRequestResource(@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z]+){6,16}\$")
                                        @get:NotBlank
                                        @JsonProperty("transactionAmount")
                                        @JsonPropertyDescription("value of transaction")
                                        val transactionAmount: Decimal128,

                                        @get:Pattern(regexp = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#\$%!?]).{8,40})")
                                        @get:NotBlank
                                        @JsonProperty("username")
                                        @JsonPropertyDescription("alias/on screen name for account")
                                        val username: String,

                                        @get:Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+){6,16}\$")
                                        @get:NotBlank
                                        @JsonProperty("password")
                                        @JsonPropertyDescription("alias/on screen name for account")
                                        val password: String
) {
    fun toReduceCommand(): ReduceBalanceUseCase.ReduceBalanceCommand {
        return ReduceBalanceUseCase.ReduceBalanceCommand(
                Mono.just(ChangeBalanceRequestResource(this.transactionAmount, this.username, this.password)))

    }

    fun toIncreaseCommand(): IncreaseBalanceUseCase.IncreaseBalanceCommand {
        return IncreaseBalanceUseCase.IncreaseBalanceCommand(
                Mono.just(ChangeBalanceRequestResource(this.transactionAmount, this.username, this.password)))

    }
}