package com.guacamole.api.product.domain.product

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun saveAndFlush(product: Product): Product =
        productRepository.saveAndFlush(product)

    fun findById(productId: Long): Product =
        productRepository.findByIdOrNull(productId)
            ?: throw RuntimeException("Not Found Product")

    fun remove(productId: Long) =
        productRepository.deleteById(productId)

    fun update(product: Product): Product =
        productRepository.saveAndFlush(product)

    fun existsById(productId: Long): Boolean =
        productRepository.existsById(productId)
}
