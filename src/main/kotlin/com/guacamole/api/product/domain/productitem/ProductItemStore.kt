package com.guacamole.api.product.domain.productitem

interface ProductItemStore {
    fun saveAndFlush(productItem: ProductItem): ProductItem
    fun findById(productItemId: Long): ProductItem
    fun remove(productItemId: Long)
    fun existById(productItemId: Long): Boolean
}
