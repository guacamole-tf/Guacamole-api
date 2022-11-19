package com.guacamole.api.production.domain.product

class ProductItem(
    var product_id: Long,
    var stock_id: Long,
    var size_unit: Long,
    var size_rate: Int,
    var price: Int,
    var id: Long? = null
) {
}