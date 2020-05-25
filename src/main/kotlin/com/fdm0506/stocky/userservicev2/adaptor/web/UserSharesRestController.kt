package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.adaptor.persistence.SharesRepository
import com.fdm0506.stocky.userservicev2.application.port.`in`.UpdateUserSharesUseCase
import com.fdm0506.stocky.userservicev2.domain.model.UserShares
import com.fdm0506.stocky.userservicev2.domain.response.UpdateSharesResponse
import org.bson.types.Decimal128
import org.bson.types.ObjectId
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class UserSharesRestController(val updateUserSharesUseCase: UpdateUserSharesUseCase,
                               val sharesRepository: SharesRepository) {

    @PutMapping(value = ["/api/v2/users/shares/update"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateUserShares(@RequestBody request: UpdateSharesRequestResource): Mono<UpdateSharesResponse> {
        return updateUserSharesUseCase.updateHeldShares(request.toCommand())
    }

    @GetMapping(value = ["/api/v2/users/shares/all/{username}"], produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun getSharesForUser(@PathVariable username: String): Mono<UserShares> {
        return sharesRepository.findByUser(username)
    }

}