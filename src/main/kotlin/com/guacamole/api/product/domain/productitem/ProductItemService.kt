package com.guacamole.api.product.domain.productitem

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ProductItemService(
    private val productRepository: ProductItemRepository
) {
    fun saveAndFlush(productItem: ProductItem): ProductItem =
        productRepository.saveAndFlush(productItem)
}
