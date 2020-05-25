package com.fdm0506.stocky.userservicev2.adaptor.web

import com.fdm0506.stocky.userservicev2.application.port.`in`.UpdateUserSharesUseCase
import com.fdm0506.stocky.userservicev2.domain.model.TransactionType
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.bson.types.Decimal128
import reactor.core.publisher.Mono

@Data
@AllArgsConstructor
@NoArgsConstructor
data class UpdateSharesRequestResource(
        val username: String,
        val stockSymbol: String,
        val volume: Int,
        val currentPrice: Number,
        val type: TransactionType
) {
    fun toCommand(): UpdateUserSharesUseCase.UpdateSharesCommand {
        return UpdateUserSharesUseCase.UpdateSharesCommand(
                Mono.just(UpdateSharesRequestResource(
                        this.username, this.stockSymbol, this.volume, this.currentPrice, this.type)))
    }
}