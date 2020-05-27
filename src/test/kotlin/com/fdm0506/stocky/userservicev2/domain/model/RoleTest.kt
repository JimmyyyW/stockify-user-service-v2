package com.fdm0506.stocky.userservicev2.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RoleTest {

    @Test
    internal fun instantiate() {
        val role = Role(RoleType.ADMIN)

        assertEquals(role.role, RoleType.ADMIN)
    }
}