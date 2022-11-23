package com.guacamole.api.product.application

import com.guacamole.api.product.application.command.ProductItemCommand
import com.guacamole.api.product.domain.product.ProductService
import com.guacamole.api.product.domain.productitem.ProductItemService
import com.guacamole.api.product.domain.productitempolicy.ProductItemPolicyService
import com.guacamole.api.product.domain.stock.StockService
import org.springframework.stereotype.Service

@Service
class ProductItemFacadeService(
    private val productService: ProductService,
    private val stockService: StockService,
    private val productItemService: ProductItemService,
    private val productItemPolicyService: ProductItemPolicyService
) {

    // 트랜잭셔널
    fun registrationProductItem(productItemCommand: ProductItemCommand): Long {
        if (productService.existsById(productItemCommand.productId)) {
            throw RuntimeException()
        }

        // 트랜잭션 핸들러로 동작
        val stock = stockService.saveAndFlush(productItemCommand.toStock())
        val productItem = productItemService.saveAndFlush(productItemCommand.toProductItem(stock.id!!))
        val productItemPolicy =
            productItemPolicyService.saveAndFlush(productItemCommand.toProductItemPolicy(productItem.id!!))
        return productItem.id!!
    }
}
