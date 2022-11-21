package com.guacamole.api.production.product.application

import com.guacamole.api.production.product.domain.command.ProductCommand
import com.guacamole.api.production.product.domain.store.ProductStore
import org.springframework.stereotype.Service

@Service
class ProductFacadeService(
    private val productStore: ProductStore
) {

    // 트랜잭셔널
    fun create(productCommand: ProductCommand): Long {
        val product = productCommand.toEntity()
        return productStore.save(product).id!!
    }
}
