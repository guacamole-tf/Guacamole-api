package com.guacamole.api.production.product.application

import com.guacamole.api.production.product.domain.Category
import com.guacamole.api.production.product.domain.store.CategoryStore

class CategoryMapStore : CategoryStore {

    private var store: MutableMap<Long, Category> = HashMap()

    override fun save(category: Category): Category {
        if (store.containsKey(category.id)) {
            store.put(category.id!!, category)
            return category
        }

        store += mapOf(store.size.toLong() + 1 to category)
        return Category(category.name, category.parentId, store.size.toLong())
    }

    override fun findById(categoryId: Long): Category {
        if (store.containsKey(categoryId)) {
            return store.get(categoryId)!!
        }
        throw RuntimeException()
    }
}
