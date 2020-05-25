package com.fdm0506.stocky.userservicev2.domain.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.bson.types.Decimal128
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user-shares")
data class UserShares (
        @Id val id: String,
        val user: String,
        val shares: MutableMap<String, Int>
)