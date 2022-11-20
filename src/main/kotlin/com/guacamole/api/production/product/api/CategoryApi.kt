package com.guacamole.api.production.product.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/api/categories")
@RestController
class CategoryApi {

    @PostMapping
    fun createCategoryApi(): ResponseEntity<Any> {
        return ResponseEntity.created(URI.create("")).build()
    }
}
