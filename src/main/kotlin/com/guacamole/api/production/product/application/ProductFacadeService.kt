package com.guacamole.api.production.product.application

import com.guacamole.api.production.product.domain.command.ProductCommand
import com.guacamole.api.production.product.domain.store.ProductStore
import org.springframework.stereotype.Service

@Service
class ProductFacadeService(
    private val productStore: ProductStore,
    private val categoryService: CategoryService
) {

    fun create(productCommand: ProductCommand): Long {
        val product = productCommand.toEntity()
        return productStore.save(product).id!!
    }

    fun update(productId: Long, productCommand: ProductCommand) {
        val product = productStore.findById(productId)
        if (product.categoryId != productCommand.categoryId) {
            categoryService.verifyCategoryId(productCommand.categoryId)
        }
        product.update(
            productCommand.categoryId,
            productCommand.name,
            productCommand.descriptionImagePath,
            productCommand.originPlace,
            productCommand.detailDescription
        )
        productStore.save(product)
    }

    fun delete(productId: Long) {
        productStore.remove(productId)
    }
}
