package com.guacamole.api.product.domain

class ProductItem(
    var productId: Long,
    var stockId: Long,
    var sizeUnit: Long,
    var sizeRate: Int,
    var price: Int,
    var id: Long? = null
) {
}