package com.guacamole.api.product.domain.category

import com.guacamole.api.product.application.command.CategoryCommand
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun save(categoryCommand: CategoryCommand): Long =
        categoryRepository.saveAndFlush(categoryCommand.toCategory()).id!!

    fun update(categoryId: Long, categoryCommand: CategoryCommand) {
        val category = categoryRepository.findByIdOrNull(categoryId)
            ?: throw RuntimeException("Not Found Category")
        verifyCategoryParentId(categoryCommand.parentId)
        categoryRepository.saveAndFlush(category.update(categoryCommand.name, categoryCommand.parentId))
    }

    private fun verifyCategoryParentId(categoryId: Long) {
        if (categoryId == 0L || existsById(categoryId)) {
            return
        }
        throw RuntimeException("Invalid Category")
    }

    fun existsById(categoryId: Long): Boolean =
        categoryRepository.existsById(categoryId)

    fun deleteById(categoryId: Long) =
        categoryRepository.deleteById(categoryId)
}
