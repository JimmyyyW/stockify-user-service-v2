package com.fdm0506.stocky.userservicev2.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(value = "global-config")
data class GlobalConfig(
        val host: String = "http://localhost:5000"
) {

}