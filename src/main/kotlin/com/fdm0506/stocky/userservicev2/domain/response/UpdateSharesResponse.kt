package com.fdm0506.stocky.userservicev2.domain.response

import com.fasterxml.jackson.annotation.JsonProperty
import jdk.jfr.Description
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.bson.types.Decimal128

@Data
@AllArgsConstructor
@NoArgsConstructor
data class UpdateSharesResponse(

        @JsonProperty("outcome")
        @Description("result of server transaction")
        val outcome: String,

        @JsonProperty("shares")
        @Description("updated shares belonging to user")
        val shares: Map<String, Int>
)