package com.guacamole.api.product.application.command

import com.guacamole.api.product.domain.productitem.ProductItem
import com.guacamole.api.product.domain.productitempolicy.ProductItemPolicy
import com.guacamole.api.product.domain.stock.Stock

data class ProductItemCommand(
    val price: Int,
    val count: Int,
    val thumbnailImagePath: String,
    val sizeUnit: String,
    val sizeRate: Int,
) {
    fun toStock(): Stock =
        Stock(count)

    fun toProductItem(productId: Long, stockId: Long): ProductItem =
        ProductItem(productId, stockId, thumbnailImagePath, sizeUnit, sizeRate, price)

    fun toProductItemPolicy(productItemId: Long): ProductItemPolicy =
        ProductItemPolicy(productItemId, "", 1, true, "", 10)
}
