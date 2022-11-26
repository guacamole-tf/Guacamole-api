package com.guacamole.api.product.api.request.productitem

import com.guacamole.api.product.application.command.ProductItemCommand
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ProductItemUpdateRequest(

    @field:Min(0)
    @field:NotNull
    val price: Int,

    @field:Min(0)
    @field:NotNull
    val count: Int,

    @field:NotBlank
    val thumbnailImagePath: String,

    @field:NotNull
    val sizeUnit: String,

    @field:Min(0)
    @field:NotNull
    val sizeRate: Int
) {
    fun toCommand(): ProductItemCommand =
        ProductItemCommand(price, count, thumbnailImagePath, sizeUnit, sizeRate)
}
