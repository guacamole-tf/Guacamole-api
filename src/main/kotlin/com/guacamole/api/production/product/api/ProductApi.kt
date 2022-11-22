package com.guacamole.api.production.product.api

import com.guacamole.api.production.product.api.request.product.ProductCreateRequest
import com.guacamole.api.production.product.api.request.product.ProductUpdateRequest
import com.guacamole.api.production.product.application.ProductFacadeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RequestMapping("/api/products")
@RestController
class ProductApi(
    private val productFacadeService: ProductFacadeService
) {

    @PostMapping
    fun createProduct(@RequestBody request: ProductCreateRequest): ResponseEntity<Any> {
        val productId = productFacadeService.create(request.toCommand())
        return ResponseEntity.created(URI.create("/api/products/${productId}")).build()
    }

    @PutMapping("/{productId}")
    fun updateProduct(
        @PathVariable productId: Long,
        @RequestBody request: ProductUpdateRequest
    ): ResponseEntity<Any> {
        productFacadeService.update(productId, request.toCommand())
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}")
    fun deleteProduct(@PathVariable productId: Long): ResponseEntity<Any> {
        productFacadeService.delete(productId)
        return ResponseEntity.noContent().build();
    }
}
