package com.guacamole.api.product.domain.command

import com.guacamole.api.product.domain.Product

data class ProductCommand(
    val categoryId: Long,
    val name: String,
    val descriptionImagePath: String,
    val originPlace: String,
    val detailDescription: String,
) {
    fun toProduct(): Product {
        return Product(categoryId, name, descriptionImagePath, originPlace, detailDescription)
    }
}
