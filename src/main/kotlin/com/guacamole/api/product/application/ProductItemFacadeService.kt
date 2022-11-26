package com.guacamole.api.product.application

import com.guacamole.api.common.TransactionHandler
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
    private val productItemPolicyService: ProductItemPolicyService,
    private val transactionHandler: TransactionHandler
) {

    fun registrationProductItem(productItemCommand: ProductItemCommand): Long {
        if (!productService.existsById(productItemCommand.productId)) {
            throw RuntimeException()
        }

        return transactionHandler.runInTransaction {
            val stock = stockService.saveAndFlush(productItemCommand.toStock())
            val productItem = productItemService.saveAndFlush(productItemCommand.toProductItem(stock.id!!))
            productItemPolicyService.saveAndFlush(productItemCommand.toProductItemPolicy(productItem.id!!))
            return@runInTransaction productItem.id!!
        }
    }
}
