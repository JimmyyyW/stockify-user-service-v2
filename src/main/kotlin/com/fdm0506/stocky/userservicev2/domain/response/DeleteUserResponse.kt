package com.fdm0506.stocky.userservicev2.domain.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import lombok.AllArgsConstructor
import lombok.Data

@Data
@AllArgsConstructor
data class DeleteUserResponse(@JsonProperty("outcome")
                              @JsonPropertyDescription("result of user deletion attempt")
                              val outcome: String?,

                              @JsonProperty("user")
                              @JsonPropertyDescription("user object")
                              val _id: String)

@Data
@AllArgsConstructor
data class DeleteAllUserByUsernameResponse(@JsonProperty("outcome")
                                           @JsonPropertyDescription("result of user deletion attempt")
                                           val outcome: String?,

                                           @JsonProperty("username")
                                           @JsonPropertyDescription("username deleted")
                                           val username: String)
