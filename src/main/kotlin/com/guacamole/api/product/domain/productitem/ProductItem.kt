package com.guacamole.api.product.domain.productitem

class ProductItem(
    var productId: Long,
    var stockId: Long,
    var sizeUnit: String,
    var sizeRate: Int,
    var price: Int,
    var id: Long? = null
) {
}