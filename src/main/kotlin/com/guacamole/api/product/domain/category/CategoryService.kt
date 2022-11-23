package com.guacamole.api.product.domain.category

import com.guacamole.api.product.application.command.CategoryCommand
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryStore: CategoryStore = CategoryMapStore()
) {

    fun saveAndFlush(categoryCommand: CategoryCommand): Long =
        categoryStore.saveAndFlush(categoryCommand.toCategory()).id!!

    fun update(categoryCommand: CategoryCommand, categoryId: Long) {
        val category = categoryStore.findById(categoryId)
        verifyCategoryParentId(categoryCommand.parentId)
        category.update(categoryCommand.name, categoryCommand.parentId)
        categoryStore.saveAndFlush(category)
    }

    private fun verifyCategoryParentId(categoryId: Long) {
        if (categoryId == 0L || existsById(categoryId)) {
            return
        }
        throw RuntimeException()
    }

    fun existsById(categoryId: Long): Boolean =
        categoryStore.existsById(categoryId)

    fun remove(categoryId: Long) =
        categoryStore.remove(categoryId)
}
