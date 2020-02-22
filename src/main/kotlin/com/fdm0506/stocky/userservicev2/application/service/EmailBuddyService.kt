package com.fdm0506.stocky.userservicev2.application.service

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fdm0506.stocky.userservicev2.configuration.GlobalConfig
import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class EmailBuddyService(config: GlobalConfig) {

    private val webClient: WebClient = WebClient
            .builder()
            .baseUrl(config.host)
            .build()

    fun sendActivationEmail(email: String): Mono<ActivationEmailResponse> {
        return webClient.post()
                .uri("/registered")
                .body(BodyInserters.fromValue("{\n\"target\": \"$email\"\n}"))
                .retrieve()
                .bodyToMono(ActivationEmailResponse::class.java)
    }
}

@Data
@AllArgsConstructor
data class ActivationEmailResponse(
        @JsonProperty("details")
        @JsonPropertyDescription("result of attempt to send registration email")
        val details: String
)
//TODO: desired response (change emailbuddy)
//@Data
//@AllArgsConstructor
//data class ActivationEmailResponse(
//        @JsonProperty("outcome")
//        @JsonPropertyDescription("result of attempt to send registration email")
//        val outcome: String,
//
//        @JsonProperty("username")
//        @JsonPropertyDescription("username of register")
//        val username: String,
//
//        @JsonProperty("email")
//        @JsonPropertyDescription("email activation link was sent to")
//        val email: String
//)

@Data
@AllArgsConstructor
data class ActivationEmailRequest(
        @JsonProperty("target")
        @JsonPropertyDescription("destination address for activation email")
        val target: String
)