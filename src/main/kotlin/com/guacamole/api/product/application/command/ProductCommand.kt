package com.guacamole.api.product.application.command

import com.guacamole.api.product.domain.product.Product

data class ProductCommand(
    val categoryId: Long,
    val name: String,
    val descriptionImagePath: String,
    val originPlace: String,
    val detailDescription: String,
) {
    fun toProduct(): Product =
        Product(categoryId, name, descriptionImagePath, originPlace, detailDescription)
}
