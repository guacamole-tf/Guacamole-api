package com.guacamole.api.product.domain.productitem

import org.springframework.stereotype.Service

@Service
class ProductItemService(
    private val productItemStore: ProductItemStore = ProductItemMapStore()
) {
    fun saveAndFlush(productItem: ProductItem): ProductItem =
        productItemStore.saveAndFlush(productItem)
}
