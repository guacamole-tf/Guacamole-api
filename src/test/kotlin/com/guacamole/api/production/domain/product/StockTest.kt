package com.guacamole.api.production.domain.product

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class StockTest {
    @Test
    fun `재고는 0 이하의 개수로 생성할 수 없다`() {
        assertThrows<RuntimeException> {
            createStock(-1)
        }
    }

    fun createStock(
        count: Int = 0,
        id: Long = 1
    ): Stock {
        return Stock(count, id)
    }
}

