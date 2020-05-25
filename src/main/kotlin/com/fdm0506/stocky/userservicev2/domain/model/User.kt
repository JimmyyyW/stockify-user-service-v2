package com.fdm0506.stocky.userservicev2.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import lombok.NoArgsConstructor
import org.bson.types.Decimal128
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.IndexDirection
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Document(collection = "users-v2")
@NoArgsConstructor
data class User(@Id val _id: ObjectId,
                val name: String,
                val surname: String,
                val username: String,
                val password: String,
                val email: String,
                var balance: Number? = Decimal128.parse("50000.00"),
                var enabled: Boolean? = false,
                val timeStamp: LocalDateTime? = LocalDateTime.now(),
                val roles: Set<Role>? = setOf(Role(ObjectId("5dd937401c9d440000fce615"), "MEMBER"))
)
