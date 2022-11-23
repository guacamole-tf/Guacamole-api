package com.guacamole.api.product.domain.stock

import java.util.concurrent.atomic.AtomicInteger

class StockMapStore : StockStore {

    private var store: MutableMap<Long, Stock> = HashMap()
    private var count: AtomicInteger = AtomicInteger()

    override fun saveAndFlush(stock: Stock): Stock {
        if (store.containsKey(stock.id)) {
            store[stock.id!!] = stock
            return Stock(stock.count, count.get().toLong())
        }

        store[count.incrementAndGet().toLong()] = stock
        return Stock(stock.count, count.get().toLong())
    }

    override fun findById(stockId: Long): Stock {
        if (store.containsKey(stockId)) {
            return store[stockId]!!
        }
        throw RuntimeException()
    }

    override fun remove(stockId: Long) {
        if (store.containsKey(stockId)) {
            store.remove(stockId)
            return
        }
        throw RuntimeException()
    }

    override fun existsById(stockId: Long): Boolean =
        store.containsKey(stockId)
}
