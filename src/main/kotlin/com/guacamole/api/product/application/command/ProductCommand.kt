package com.guacamole.api.product.application.command

import com.guacamole.api.common.extension.greaterOrEqual
import com.guacamole.api.product.domain.product.Product

data class ProductCommand(
    val categoryId: Long,
    val name: String,
    val descriptionImagePath: String,
    val originPlace: String,
    val detailDescription: String,
) {
    init {
        require(categoryId.greaterOrEqual(0))
        require(name.isNotBlank())
    }

    fun toProduct(): Product =
        Product(categoryId, name, descriptionImagePath, originPlace, detailDescription)
}
