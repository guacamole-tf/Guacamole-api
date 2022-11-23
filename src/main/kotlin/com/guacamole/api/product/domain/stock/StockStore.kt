package com.guacamole.api.product.domain.stock

interface StockStore {
    fun saveAndFlush(stock: Stock): Stock
    fun findById(stockId: Long): Stock
    fun remove(stockId: Long)
    fun existsById(stockId: Long): Boolean
}
