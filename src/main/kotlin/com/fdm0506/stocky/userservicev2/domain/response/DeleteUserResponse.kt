package com.fdm0506.stocky.userservicev2.domain.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import lombok.AllArgsConstructor
import lombok.Data
import org.bson.types.ObjectId

@Data
@AllArgsConstructor
data class DeleteUserResponse(@JsonProperty("outcome")
                              @JsonPropertyDescription("result of user registration attempt")
                              val outcome: String?,

                              @JsonProperty("user")
                              @JsonPropertyDescription("user object")
                              val _id: ObjectId?)
