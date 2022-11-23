package com.guacamole.api.product.application

import com.guacamole.api.product.application.command.ProductCommand
import com.guacamole.api.product.domain.category.CategoryService
import com.guacamole.api.product.domain.product.ProductService
import org.springframework.stereotype.Service

@Service
class ProductFacadeService(
    private val productService: ProductService,
    private val categoryService: CategoryService
) {

    fun registrationProduct(productCommand: ProductCommand): Long =
        productService.saveAndFlush(productCommand.toProduct()).id!!

    fun updateProduct(productId: Long, productCommand: ProductCommand) {
        val product = productService.findById(productId)
        if (product.categoryId != productCommand.categoryId) {
            verifyAcceptableCategoryId(productCommand)
        }
        product.update(
            productCommand.categoryId,
            productCommand.name,
            productCommand.descriptionImagePath,
            productCommand.originPlace,
            productCommand.detailDescription
        )
        productService.update(product)
    }

    private fun verifyAcceptableCategoryId(productCommand: ProductCommand) {
        if (categoryService.existsById(productCommand.categoryId)) {
            return
        }
        throw RuntimeException()
    }

    fun removeProduct(productId: Long) {
        productService.remove(productId)
    }
}
