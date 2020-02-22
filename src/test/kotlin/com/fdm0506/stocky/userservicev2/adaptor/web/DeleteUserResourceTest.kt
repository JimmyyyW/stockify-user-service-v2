package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.CreateUserUseCase
import com.fdm0506.stocky.userservicev2.application.port.`in`.DeleteUserUseCase
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

internal class DeleteUserResourceTest {

    private val unit: DeleteUserResource = DeleteUserResource()

    @Test
    fun toCommand_returnsCommand_forDeleteUserResource() {
        val id = ObjectId("5e370047bfc495694fe1c43e")
        val monoId: Mono<ObjectId> = Mono.just(id)
        val cmd: DeleteUserUseCase.DeleteUserCommand = unit.toCommand (id)
        assertEquals(DeleteUserUseCase.DeleteUserCommand::class.java, cmd.javaClass)
        assertEquals(monoId.block(), cmd.user.block())
    }
}