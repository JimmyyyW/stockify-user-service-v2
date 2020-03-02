package com.fdm0506.stocky.userservicev2

import org.springframework.boot.autoconfigure.AutoConfigurationPackages
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class UserServiceV2Application

@Bean
fun bCryptPasswordEncoder(): BCryptPasswordEncoder { return BCryptPasswordEncoder() }

fun main(args: Array<String>) {
    runApplication<UserServiceV2Application>(*args)
}
