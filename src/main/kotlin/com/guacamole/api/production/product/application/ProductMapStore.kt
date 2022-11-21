package com.guacamole.api.production.product.application

import com.guacamole.api.production.product.domain.Product
import com.guacamole.api.production.product.domain.store.ProductStore
import org.springframework.stereotype.Service

@Service
class ProductMapStore : ProductStore {
    override fun save(product: Product): Product {
        return Product(product.categoryId, product.name, product.thumbnailImagePath, product.descriptionImagePath, product.originPlace, product.detailDescription, 1L)
    }

    override fun findById(productId: Long): Product {
        TODO("Not yet implemented")
    }

    override fun remove(productId: Long) {
        TODO("Not yet implemented")
    }
}