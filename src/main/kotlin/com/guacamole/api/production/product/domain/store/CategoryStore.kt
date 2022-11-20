package com.guacamole.api.production.product.domain.store

import com.guacamole.api.production.product.domain.Category

interface CategoryStore {
    fun save(category: Category): Category
    fun findById(categoryId: Long): Category
}
