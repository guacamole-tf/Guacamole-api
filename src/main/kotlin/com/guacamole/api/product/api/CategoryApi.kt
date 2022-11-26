package com.guacamole.api.product.api

import com.guacamole.api.product.api.request.category.CategoryCreateRequest
import com.guacamole.api.product.api.request.category.CategoryUpdateRequest
import com.guacamole.api.product.domain.category.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RequestMapping("/api/categories")
@RestController
class CategoryApi(
    private val categoryService: CategoryService
) {
    @PostMapping
    fun createCategory(@RequestBody @Valid request: CategoryCreateRequest): ResponseEntity<Any> {
        val categoryId = categoryService.save(request.toCommand())
        return ResponseEntity.created(URI.create("/api/categories/${categoryId}")).build()
    }

    @PutMapping("/{categoryId}")
    fun updateCategory(
        @RequestBody @Valid request: CategoryUpdateRequest,
        @PathVariable categoryId: Long
    ): ResponseEntity<Any> {
        categoryService.update(categoryId, request.toCommand())
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{categoryId}")
    fun deleteCategory(@PathVariable categoryId: Long): ResponseEntity<Any> {
        categoryService.deleteById(categoryId)
        return ResponseEntity.noContent().build()
    }
}
