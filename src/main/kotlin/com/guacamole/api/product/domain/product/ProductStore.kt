package com.guacamole.api.product.domain.product

interface ProductStore {
    fun saveAndFlush(product: Product): Product
    fun findById(productId: Long): Product
    fun remove(productId: Long)
    fun existById(productId: Long): Boolean
}
