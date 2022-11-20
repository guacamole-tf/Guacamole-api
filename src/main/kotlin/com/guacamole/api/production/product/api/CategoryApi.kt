package com.guacamole.api.production.product.api

import com.guacamole.api.production.product.application.CategoryService
import com.guacamole.api.production.product.application.request.CategoryCreateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
}
