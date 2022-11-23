package com.guacamole.api.product.application

import com.guacamole.api.product.domain.command.CategoryCommand
import com.guacamole.api.product.domain.store.CategoryStore
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryStore: CategoryStore = CategoryMapStore()
) {

    fun createCategory(categoryCommand: CategoryCommand): Long =
        categoryStore.save(categoryCommand.toCategory()).id!!

    fun updateCategory(categoryCommand: CategoryCommand, categoryId: Long) {
        val category = categoryStore.findById(categoryId)
        verifyCategoryId(categoryCommand.parentId)
        category.update(categoryCommand.name, categoryCommand.parentId)
        categoryStore.save(category)
    }

    fun deleteCategory(categoryId: Long) {
        categoryStore.remove(categoryId)
    }

    fun verifyCategoryId(categoryId: Long) {
        if (categoryId == 0L || categoryStore.existsById(categoryId)) {
            return
        }
        throw RuntimeException()
    }
}
