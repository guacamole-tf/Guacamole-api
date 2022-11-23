package com.guacamole.api.product.domain.product

import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productStore: ProductStore = ProductMapStore()
) {
    fun saveAndFlush(product: Product): Product =
        productStore.saveAndFlush(product)

    fun findById(productId: Long): Product =
        productStore.findById(productId)

    fun remove(productId: Long) =
        productStore.remove(productId)

    fun update(product: Product): Product =
        productStore.saveAndFlush(product)

    fun existsById(productId: Long): Boolean =
        productStore.existById(productId)
}
