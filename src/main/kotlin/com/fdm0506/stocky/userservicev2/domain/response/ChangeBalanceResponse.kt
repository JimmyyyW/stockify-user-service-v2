package com.fdm0506.stocky.userservicev2.domain.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import lombok.AllArgsConstructor
import lombok.Data
import org.bson.types.Decimal128

@Data
@AllArgsConstructor
data class ChangeBalanceResponse(
        @JsonProperty("outcome")
        @JsonPropertyDescription("outcome of request")
        val outcome: String,

        @JsonProperty("balance")
        @JsonPropertyDescription("balance after transaction")
        val balance: Decimal128

//        @JsonProperty("type")
//        @JsonPropertyDescription("whether payment was in or out")
//        val type: BalanceRequestType,

//        @JsonProperty("amount")
//        @JsonPropertyDescription("value of transaction")
//        val amount: Decimal128?
)