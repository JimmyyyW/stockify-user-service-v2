package com.fdm0506.stocky.userservicev2.application.service

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fdm0506.stocky.userservicev2.configuration.GlobalConfig
import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.Disposable
import reactor.core.publisher.Mono
import reactor.core.publisher.doOnError
import java.lang.RuntimeException

@Service
class EmailBuddyService(config: GlobalConfig) {

    private val webClient: WebClient = WebClient
            .builder()
            .baseUrl(config.host)
            .build()

    //todo: catch errors and on success & error log
    fun sendActivationEmail(emailAddress: String, userObjectId: String): Disposable {
        return webClient.post()
                .uri("/registered")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(ActivationEmailRequest(target = emailAddress, code = userObjectId)))
                .retrieve()
                .bodyToMono(ActivationEmailResponse::class.java)
                .doOnError { err -> println(err.message + " | Check EmailBuddy running")}
                .subscribe()
    }

    fun sendErrorEmail(message: String): Disposable {
        return webClient.post()
                .uri("/error")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(ErrorEmailRequest(target = "fdm0506stockify@gmail.com",
                        message = message)))
                .retrieve()
                .bodyToMono(ErrorEmailResponse::class.java)
                .doOnError { err -> println(err.message + " | Check Email buddy running") }
                .subscribe()
    }
}

@Data
@AllArgsConstructor
data class ActivationEmailResponse(
        @JsonProperty("details")
        @JsonPropertyDescription("result of attempt to send registration email")
        val details: String
)

@Data
@AllArgsConstructor
data class ActivationEmailRequest(
        @JsonProperty("target")
        @JsonPropertyDescription("result of attempt to send registration email")
        val target: String,

        @JsonProperty("code")
        @JsonPropertyDescription("the code to append to activation link uri")
        val code: String
)

@Data
@AllArgsConstructor
data class ErrorEmailResponse(
        @JsonProperty("details")
        @JsonPropertyDescription("result of attempt to send registration email")
        val details: String
)

@Data
@AllArgsConstructor
data class ErrorEmailRequest(
        @JsonProperty("target")
        @JsonPropertyDescription("result of attempt to send registration email")
        val target: String,

        @JsonProperty("message")
        @JsonPropertyDescription("the message of the error")
        val message: String
)
