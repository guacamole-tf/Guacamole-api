package com.guacamole.api.product.domain.product

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun save(product: Product): Product =
        productRepository.saveAndFlush(product)

    fun update(
        productId: Long,
        categoryId: Long,
        name: String,
        descriptionImagePath: String,
        originPlace: String,
        detailDescription: String
    ) {
        val product = (productRepository.findByIdOrNull(productId)
            ?: throw RuntimeException("Not Found Product"))

        productRepository.saveAndFlush(
            product.update(
                categoryId,
                name,
                descriptionImagePath,
                originPlace,
                detailDescription
            )
        )
    }

    fun existsById(productId: Long): Boolean =
        productRepository.existsById(productId)

    fun deleteById(productId: Long) =
        productRepository.deleteById(productId)
}
