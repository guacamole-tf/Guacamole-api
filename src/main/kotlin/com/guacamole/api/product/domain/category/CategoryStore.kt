package com.guacamole.api.product.domain.category

interface CategoryStore {
    fun saveAndFlush(category: Category): Category
    fun findById(categoryId: Long): Category
    fun remove(categoryId: Long)
    fun existsById(categoryId: Long): Boolean
}
