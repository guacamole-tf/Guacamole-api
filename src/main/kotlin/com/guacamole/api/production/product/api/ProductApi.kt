package com.guacamole.api.production.product.api

import com.guacamole.api.production.product.api.request.product.ProductCreateRequest
import com.guacamole.api.production.product.application.ProductFacadeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/api/products")
@RestController
class ProductApi(
    private val productFacadeService: ProductFacadeService
) {

    @PostMapping
    fun createCategory(@RequestBody request: ProductCreateRequest): ResponseEntity<Any> {
        val productId = productFacadeService.create(request.toCommand())
        return ResponseEntity.created(URI.create("/api/products/${productId}")).build()
    }
}
