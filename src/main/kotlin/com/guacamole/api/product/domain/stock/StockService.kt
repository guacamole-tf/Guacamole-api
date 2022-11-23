package com.guacamole.api.product.domain.stock

import org.springframework.stereotype.Service

@Service
class StockService(
    private val stockStore: StockStore = StockMapStore()
) {
    fun saveAndFlush(stock: Stock): Stock = stockStore.saveAndFlush(stock)
}
