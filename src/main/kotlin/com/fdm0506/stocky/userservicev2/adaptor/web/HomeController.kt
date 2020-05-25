package com.fdm0506.stocky.userservicev2.adaptor.web

import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.awt.PageAttributes

@RestController
class HomeController {

    @GetMapping(value = ["/home"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun homePageMessage(): Mono<HomeMessage> {
        return Mono.just(HomeMessage("Welcome to Stockify"))
    }

    @Data
    @AllArgsConstructor
    data class HomeMessage(val message: String)
}