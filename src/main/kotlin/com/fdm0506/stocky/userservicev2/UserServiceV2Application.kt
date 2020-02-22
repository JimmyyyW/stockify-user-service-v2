package com.fdm0506.stocky.userservicev2

import org.springframework.boot.autoconfigure.AutoConfigurationPackages
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserServiceV2Application

fun main(args: Array<String>) {
    runApplication<UserServiceV2Application>(*args)
}
