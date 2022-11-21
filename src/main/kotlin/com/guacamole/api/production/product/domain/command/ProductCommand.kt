package com.guacamole.api.production.product.domain.command

import com.guacamole.api.production.product.domain.Product

data class ProductCommand(
    val categoryId: Long,
    val name: String,
    val descriptionImagePath: String,
    val originPlace: String,
    val detailDescription: String,
) {
    fun toEntity(): Product {
        return Product(categoryId, name, descriptionImagePath, originPlace, detailDescription)
    }
}
