package com.guacamole.api.product.domain

class ProductItemPolicy(
    var productItemId: Long,
    var limitUnit: String,
    var limitCount: Int,
    var bannedAt: Boolean,
    var discountUnit: String,
    var discountRate: Int,
    var id: Long? = null
) {
}