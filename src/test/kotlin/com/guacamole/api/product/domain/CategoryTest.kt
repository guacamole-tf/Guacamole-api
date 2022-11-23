package com.guacamole.api.product.domain

import com.guacamole.api.product.domain.Category
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CategoryTest {

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "   ", "    "])
    fun `카테고리의 이름이 공백으로만 이루어져 있다면 에러를 발생한다`(name: String) {
        assertThrows<RuntimeException> {
            createCategory(name)
        }
    }

    @ParameterizedTest
    @ValueSource(longs = [-1, -2, -10, -100, Long.MIN_VALUE])
    fun `카테고리의 상위 카테고리 번호가 0 미만이라면 예외를 발생한다`(parentId: Long) {
        assertThrows<RuntimeException> {
            createCategory(parentId = parentId)
        }
    }

    fun createCategory(
        name: String = "CATEGORY",
        parentId: Long = 0L,
        id: Long = 0L
    ): Category {
        return Category(name, parentId, id)
    }
}
