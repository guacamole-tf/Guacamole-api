package com.guacamole.api.production.product.api

import com.guacamole.api.production.product.api.request.CategoryCreateRequest
import com.guacamole.api.production.product.api.request.CategoryUpdateRequest
import com.guacamole.api.production.product.application.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RequestMapping("/api/categories")
@RestController
class CategoryApi(
    private val categoryService: CategoryService
) {
    @PostMapping
    fun createCategoryApi(@RequestBody request: CategoryCreateRequest): ResponseEntity<Any> {
        val categoryId = categoryService.createCategory(request.toCommand())

        return ResponseEntity.created(URI.create("/api/categories/${categoryId}")).build()
    }

    @PutMapping("/{categoryId}")
    fun createCategoryApi(
        @RequestBody request: CategoryUpdateRequest,
        @PathVariable categoryId: Long
    ): ResponseEntity<Any> {
        categoryService.updateCategory(request.toCommand(), categoryId)
        return ResponseEntity.noContent().build()
    }
}
