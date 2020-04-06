package com.fdm0506.stocky.userservicev2.domain.model

import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RoleTest {

    @Test
    internal fun instantiate() {
        val role = Role(ObjectId("5dd937311c9d440000fce613"), "ADMIN")

        assertEquals(role.role, "ADMIN")
        assertEquals(role._id, ObjectId("5dd937311c9d440000fce613"))
    }
}