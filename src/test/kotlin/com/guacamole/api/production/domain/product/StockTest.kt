package com.guacamole.api.production.domain.product

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows


class StockTest {
    @Test
    fun `재고는 0 이하의 개수로 생성할 수 없다`() {
        assertThrows<RuntimeException> {
            createStock(-1)
        }
    }

    @Test
    fun `재고수가 1 이상이라면 재고수를 1개 감소시킬 수 있다`() {
        val stock = createStock(1)

        assertDoesNotThrow { stock.decreaseByCount() }
    }

    @Test
    fun `재고수가 0 이하라면 재고수를 감소시킬 수 없다`() {
        val stock = createStock(0)

        assertThrows<RuntimeException> {
            stock.decreaseByCount()
            stock.decreaseByCount(2)
        }
    }

    @Test
    fun `재고수보다 높은 개수 만큼 재고수를 감소시키면 에러를 발생한다`() {
        val stock = createStock(2)

        assertThrows<RuntimeException> {
            stock.decreaseByCount(3)
        }
    }

    fun createStock(
        count: Int = 0,
        id: Long = 1
    ): Stock {
        return Stock(count, id)
    }
}

