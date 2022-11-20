package com.guacamole.api.production.product.application

import com.guacamole.api.production.product.domain.command.CategoryCommand
import com.guacamole.api.production.product.domain.store.CategoryStore
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryStore: CategoryStore = CategoryMapStore()
) {

    fun createCategory(categoryCommand: CategoryCommand): Long =
        categoryStore.save(categoryCommand.toEntity()).id!!
}
