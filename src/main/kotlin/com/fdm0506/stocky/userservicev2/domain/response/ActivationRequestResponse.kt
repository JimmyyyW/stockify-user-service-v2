package com.fdm0506.stocky.userservicev2.domain.response

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Data

@Data
@AllArgsConstructor
data class ActivationRequestResponse(
        @JsonProperty("outcome")
        val outcome: String,
        @JsonProperty("detail")
        val detail: String
)