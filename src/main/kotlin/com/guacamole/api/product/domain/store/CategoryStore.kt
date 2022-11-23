package com.guacamole.api.product.domain.store

import com.guacamole.api.product.domain.Category

interface CategoryStore {
    fun save(category: Category): Category
    fun findById(categoryId: Long): Category
    fun remove(categoryId: Long)
    fun existsById(categoryId: Long): Boolean
}
