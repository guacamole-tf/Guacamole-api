package com.guacamole.api.production.product.domain.store

import com.guacamole.api.production.product.domain.Product

interface ProductStore {
    fun save(product: Product): Product
    fun findById(productId: Long): Product
    fun remove(productId: Long)
}