package com.fdm0506.stocky.userservicev2.domain.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fdm0506.stocky.userservicev2.domain.model.User

data class CreateUserResponse(
        @JsonProperty("outcome")
        @JsonPropertyDescription("result of user registration attempt")
        val outcome: String,

        @JsonProperty("user")
        @JsonPropertyDescription("user object")
        val user: User
)

