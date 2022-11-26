package com.guacamole.api.product.domain.stock

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class StockService(
    private val stockRepository: StockRepository
) {
    fun saveAndFlush(stock: Stock): Stock = stockRepository.saveAndFlush(stock)
}
