package com.guacamole.api.product.api

import com.guacamole.api.product.api.request.category.CategoryCreateRequest
import com.guacamole.api.product.api.request.category.CategoryUpdateRequest
import com.guacamole.api.product.application.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RequestMapping("/api/categories")
@RestController
class CategoryApi(
    private val categoryService: CategoryService
) {
    @PostMapping
    fun createCategory(@RequestBody request: CategoryCreateRequest): ResponseEntity<Any> {
        val categoryId = categoryService.createCategory(request.toCommand())

        return ResponseEntity.created(URI.create("/api/categories/${categoryId}")).build()
    }

    @PutMapping("/{categoryId}")
    fun updateCategory(
        @RequestBody request: CategoryUpdateRequest,
        @PathVariable categoryId: Long
    ): ResponseEntity<Any> {
        categoryService.updateCategory(request.toCommand(), categoryId)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{categoryId}")
    fun deleteCategory(@PathVariable categoryId: Long): ResponseEntity<Any> {
        categoryService.deleteCategory(categoryId)
        return ResponseEntity.noContent().build()
    }
}
