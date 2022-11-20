package com.guacamole.api.production.product.application

import com.guacamole.api.production.product.domain.Category
import com.guacamole.api.production.product.domain.store.CategoryStore

class CategoryMapStore : CategoryStore {

    private var store: Map<Long, Category> = HashMap()

    override fun save(category: Category): Category {
        store += mapOf(store.size.toLong() + 1 to category)
        return Category(category.name, category.parentId, store.size.toLong())
    }
}
