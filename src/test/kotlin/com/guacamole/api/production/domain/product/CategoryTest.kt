package com.guacamole.api.production.domain.product

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CategoryTest {


    @ParameterizedTest
    @ValueSource(strings = ["", " ", "   ", "    "])
    fun `카테고리 이름이 공백으로만 이루어져 있다면 에러를 발생한다`(name: String) {
        assertThrows<RuntimeException> {
            createCategory(name)
        }
    }

    fun createCategory(
        name: String = "CATEGORY",
        parentId: Long = 0,
        id: Long = 1,
    ): Category {
        return Category(name, parentId, id)
    }
}