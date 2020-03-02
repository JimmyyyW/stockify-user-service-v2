package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.DeleteUserUseCase
import com.fdm0506.stocky.userservicev2.domain.response.DeleteAllUserByUsernameResponse
import com.fdm0506.stocky.userservicev2.domain.response.DeleteUserResponse
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigurationPackages
import org.springframework.data.repository.query.Param
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
class DeleteUserController(private val deleteUserUseCase: DeleteUserUseCase) {

    @DeleteMapping(value = ["v2/user/delete/{user_id}"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun deleteUserById(@RequestBody @Valid @Validated deleteUserResource: DeleteUserResource, @PathVariable user_id: String) : Mono<DeleteUserResponse> {
        return deleteUserUseCase.deleteUser(deleteUserResource.toCommand((ObjectId(user_id))))
    }

    @DeleteMapping(value = ["v2/user/delete/username/{username}"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun deleteUserByUsername(@RequestBody @Valid @Validated deleteUserResource: DeleteUserByUsernameResource, @PathVariable username: String) : Mono<DeleteAllUserByUsernameResponse> {
        return deleteUserUseCase.deleteUserByUsername(deleteUserResource.toCommand(username))
    }
}