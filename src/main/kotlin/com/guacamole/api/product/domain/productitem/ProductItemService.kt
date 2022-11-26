package com.guacamole.api.product.domain.productitem

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ProductItemService(
    private val productRepository: ProductItemRepository
) {
    fun save(productItem: ProductItem): ProductItem =
        productRepository.saveAndFlush(productItem)

    fun findStockIdById(productItemId: Long): Long =
        productRepository.findStockIdByIdOrNull(productItemId)
            ?: throw RuntimeException("Not Found ProductItem")

    fun update(productItemId: Long, thumbnailImagePath: String, sizeUnit: String, sizeRate: Int, price: Int) {
        val productItem = (productRepository.findByIdOrNull(productItemId)
            ?: throw RuntimeException("Not Found ProductItem"))

        productRepository.saveAndFlush(productItem.update(thumbnailImagePath, sizeUnit, sizeRate, price))
    }
}
