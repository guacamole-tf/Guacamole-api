package com.guacamole.api.production.product.application

import com.guacamole.api.production.product.domain.Category
import com.guacamole.api.production.product.domain.store.CategoryStore
import java.util.concurrent.atomic.AtomicInteger

class CategoryMapStore : CategoryStore {

    private var store: MutableMap<Long, Category> = HashMap()
    private var count: AtomicInteger = AtomicInteger()

    override fun save(category: Category): Category {
        if (store.containsKey(category.id)) {
            store[category.id!!] = category
            return Category(category.name, category.parentId, count.get().toLong())
        }

        store[count.incrementAndGet().toLong()] = category
        return Category(category.name, category.parentId, count.get().toLong())
    }

    override fun findById(categoryId: Long): Category {
        if (store.containsKey(categoryId)) {
            return store[categoryId]!!
        }
        throw RuntimeException()
    }

    override fun remove(categoryId: Long) {
        if (store.containsKey(categoryId)) {
            store.remove(categoryId)
            return
        }
        throw RuntimeException()
    }

    override fun existsById(categoryId: Long): Boolean = store.containsKey(categoryId)
}
