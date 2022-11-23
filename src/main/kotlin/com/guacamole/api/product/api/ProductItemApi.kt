package com.guacamole.api.product.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/api/products/{productId}/items")
@RestController
class ProductItemApi(
) {

    @PostMapping
    fun createProductItem(@PathVariable productId: Long): ResponseEntity<Any> {
        return ResponseEntity.created(URI.create("/api/products/${productId}/items/1")).build()
    }
}
