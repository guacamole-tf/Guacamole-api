package com.guacamole.api.production.product.api

import com.guacamole.api.production.product.api.request.product.ProductCreateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/api/products")
@RestController
class ProductApi {

    @PostMapping
    fun createCategory(@RequestBody request: ProductCreateRequest): ResponseEntity<Any> {
        return ResponseEntity.created(URI.create("/api/products/${1}")).build()
    }
}
