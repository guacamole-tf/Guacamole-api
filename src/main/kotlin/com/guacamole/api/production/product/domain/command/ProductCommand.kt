package com.guacamole.api.production.product.domain.command

import com.guacamole.api.production.product.domain.Product

class ProductCommand(
    private val categoryId: Long,
    private val name: String,
    private val thumbnailImagePath: String,
    private val descriptionImagePath: String,
    private val originPlace: String,
    private val detailDescription: String,
) {
    fun toEntity(): Product {
        return Product(categoryId, name, thumbnailImagePath, descriptionImagePath, originPlace, detailDescription)
    }
}
