package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fdm0506.stocky.userservicev2.application.port.`in`.IncreaseBalanceUseCase
import com.fdm0506.stocky.userservicev2.application.port.`in`.ReduceBalanceUseCase
import lombok.AllArgsConstructor
import lombok.Data
import org.bson.types.Decimal128
import reactor.core.publisher.Mono
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Data
@AllArgsConstructor
data class ChangeBalanceRequestResourceNoSession(//@Pattern(regexp = "[0-9]+")
                                        //@get:NotBlank
                                        @JsonProperty("transactionAmount")
                                        @JsonPropertyDescription("value of transaction")
                                        val transactionAmount: Number,

                                        @get:Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+){6,16}\$")
                                        @get:NotBlank
                                        @JsonProperty("username")
                                        @JsonPropertyDescription("alias/on screen name for account")
                                        val username: String,

                                        @get:Pattern(regexp = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#\$%!?]).{8,40})")
                                        @get:NotBlank
                                        @JsonProperty("password")
                                        @JsonPropertyDescription("alias/on screen name for account")
                                        val password: String
) {
    fun toReduceCommand(): ReduceBalanceUseCase.ReduceBalanceCommand {
        return ReduceBalanceUseCase.ReduceBalanceCommand(
                Mono.just(ChangeBalanceRequestResourceNoSession(this.transactionAmount, this.username, this.password)))

    }

    fun toIncreaseCommand(): IncreaseBalanceUseCase.IncreaseBalanceCommand {
        return IncreaseBalanceUseCase.IncreaseBalanceCommand(
                Mono.just(ChangeBalanceRequestResourceNoSession(this.transactionAmount, this.username, this.password)))

    }
}

@Data
@AllArgsConstructor
data class ChangeBalanceRequestResourceWithSession(//@Pattern(regexp = "[0-9]+")
        //@get:NotBlank
        @JsonProperty("transactionAmount")
        @JsonPropertyDescription("value of transaction")
        val transactionAmount: Number
) {
    fun toReduceCommand(): ReduceBalanceUseCase.ReduceBalanceCommandB {
        return ReduceBalanceUseCase.ReduceBalanceCommandB(
                Mono.just(ChangeBalanceRequestResourceWithSession(this.transactionAmount)))

    }

//    fun toIncreaseCommand(): IncreaseBalanceUseCase.IncreaseBalanceCommand {
//        return IncreaseBalanceUseCase.IncreaseBalanceCommand(
//                Mono.just(ChangeBalanceRequestResourceB(this.transactionAmount, this.principalMono)))
//
//    }
}