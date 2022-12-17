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

    fun registrationProductItem(productId: Long, productItemCommand: ProductItemCommand): Long {
        if (!productService.existsById(productId)) {
            throw RuntimeException()
        }

        return transactionHandler.runInTransaction {
            val stock = stockService.save(productItemCommand.toStock())
            val productItem = productItemService.save(productItemCommand.toProductItem(productId, stock.id!!))
            productItemPolicyService.save(productItemCommand.toProductItemPolicy(productItem.id!!))
            return@runInTransaction productItem.id!!
        }
    }

    fun updateProductItem(productId: Long, productItemId: Long, productItemCommand: ProductItemCommand) {
        val stockId = productItemService.findStockIdById(productItemId)

        transactionHandler.runInTransaction {
            stockService.update(stockId, productItemCommand.count)
            productItemService.update(
                productItemId,
                productItemCommand.thumbnailImagePath,
                productItemCommand.sizeUnit,
                productItemCommand.sizeRate,
                productItemCommand.price
            )
        }
        if (productItemCommand.count > 0) {
            // TODO: Email Service 연동
        }
    }

    fun deleteProductItem(productId: Long, productItemId: Long) {
        productItemService.delete(productItemId)
    }
}
