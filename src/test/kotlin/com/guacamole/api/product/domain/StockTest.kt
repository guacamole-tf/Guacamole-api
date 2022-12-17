package com.guacamole.api.product.domain

import com.guacamole.api.fixture.createStock
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("재고 단위 테스트")
class StockTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -10, -100, Integer.MIN_VALUE])
    fun `재고는 0 미만의 개수로 생성할 수 없다`(count: Int) {
        assertThrows<RuntimeException> {
            createStock(count)
        }
    }

    @Test
    fun `재고수가 1 이상이라면 재고수를 1개 감소시킬 수 있다`() {
        val stock = createStock(count = 1)

        val actual = stock.decreaseByCount(1)

        assertThat(actual.count).isEqualTo(0)
    }

    @ParameterizedTest
    @CsvSource(value = ["1:2", "2:3", "10:11", "100:101"], delimiter = ':')
    fun `재고수보다 높은 개수 만큼 재고수를 감소시키면 에러를 발생한다`(createCount: Int, decreaseCount: Int) {
        val stock = createStock(createCount)

        assertThrows<RuntimeException> {
            stock.decreaseByCount(decreaseCount)
        }
    }

    @Test
    fun `재고수가 0 이하라면 재고수를 감소시킬 수 없다`() {
        val stock = createStock()

        assertThrows<RuntimeException> {
            stock.decreaseByCount(1)
        }
    }
}
