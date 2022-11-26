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
        productService.save(productCommand.toProduct()).id!!

    fun updateProduct(productId: Long, productCommand: ProductCommand) {
        verifyAcceptableCategoryId(productCommand.categoryId)
        productService.update(
            productId,
            productCommand.categoryId,
            productCommand.name,
            productCommand.originPlace,
            productCommand.detailDescription,
            productCommand.descriptionImagePath
        )
    }

    private fun verifyAcceptableCategoryId(categoryId: Long) {
        if (categoryService.existsById(categoryId)) {
            return
        }
        throw RuntimeException()
    }

    fun deleteProduct(productId: Long) {
        productService.deleteById(productId)
    }
}
