package com.guacamole.api.production.product.api.request.product

import com.guacamole.api.production.product.domain.command.ProductCommand
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ProductUpdateRequest(

    @field:Min(0)
    @field:NotNull
    val categoryId: Long,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val descriptionImagePath: String,

    @field:NotBlank
    val originPlace: String,

    @field:NotBlank
    val detailDescription: String,
) {
    fun toCommand(): ProductCommand  {
        return ProductCommand(categoryId, name, descriptionImagePath, originPlace, detailDescription)
    }
}
