package com.fdm0506.stocky.userservicev2.adaptor.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class HomeController {

    @GetMapping(value = ["/home"])
    fun homePageMessage(): Mono<String> {
        return Mono.just("Welcome to Stockify")
    }
}