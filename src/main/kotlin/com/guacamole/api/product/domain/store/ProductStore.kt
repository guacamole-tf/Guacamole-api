package com.guacamole.api.product.domain.store

import com.guacamole.api.product.domain.Product

interface ProductStore {
    fun save(product: Product): Product
    fun findById(productId: Long): Product
    fun remove(productId: Long)
}
