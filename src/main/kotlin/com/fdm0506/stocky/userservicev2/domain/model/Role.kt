package com.fdm0506.stocky.userservicev2.domain.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "roles")
data class Role(@Id var _id: ObjectId,
                val role: String)
