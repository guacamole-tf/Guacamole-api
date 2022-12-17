package com.guacamole.api.product.domain.stock

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Transactional
@Service
class StockService(
    private val stockRepository: StockRepository
) {
    fun save(stock: Stock): Stock = stockRepository.saveAndFlush(stock)

    fun update(stockId: Long, count: Int) {
        val stock = (
            stockRepository.findByIdOrNull(stockId)
                ?: throw EntityNotFoundException("Not Found Stock")
            )
        if (stock.count != count) {
            stockRepository.saveAndFlush(stock.update(count))
        }
    }
}
