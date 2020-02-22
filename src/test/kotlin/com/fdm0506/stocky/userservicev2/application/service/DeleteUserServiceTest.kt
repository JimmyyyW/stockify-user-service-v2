package com.fdm0506.stocky.userservicev2.application.service

import com.fdm0506.stocky.userservicev2.application.port.`in`.DeleteUserUseCase
import com.fdm0506.stocky.userservicev2.application.port.out.DeleteUserPort
import com.fdm0506.stocky.userservicev2.domain.response.DeleteUserResponse
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import reactor.core.publisher.Mono

internal class DeleteUserServiceTest {

    private var port: DeleteUserPort = mock(DeleteUserPort::class.java)
    private var unit = DeleteUserService(port)

    @Test
    internal fun validFindAllUserCommand_portCalledByService_ReturnsSuccess() {
        val command = DeleteUserUseCase.DeleteUserCommand(Mono.just(ObjectId("5e35b230bbf34d4de013f9da")))
        `when`(port.deleteUser(command.user))
                .thenReturn(Mono.just(DeleteUserResponse("success", "5e35b230bbf34d4de013f9da")))

        val actual: Mono<DeleteUserResponse> = unit.deleteUser(command)
        Mockito.verify(port, Mockito.times(1)).deleteUser(command.user)

        assertEquals("success", actual.block()?.outcome)
        assertEquals("5e35b230bbf34d4de013f9da", actual.block()?._id.toString())
    }
}