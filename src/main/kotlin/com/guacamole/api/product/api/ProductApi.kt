package com.guacamole.api.product.api

import com.guacamole.api.product.api.request.product.ProductCreateRequest
import com.guacamole.api.product.api.request.product.ProductUpdateRequest
import com.guacamole.api.product.application.ProductFacadeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RequestMapping("/api/products")
@RestController
class ProductApi(
    private val productFacadeService: ProductFacadeService
) {

    @PostMapping
    fun registrationProduct(@RequestBody @Valid request: ProductCreateRequest): ResponseEntity<Any> {
        val productId = productFacadeService.registrationProduct(request.toCommand())
        return ResponseEntity.created(URI.create("/api/products/${productId}")).build()
    }

    @PutMapping("/{productId}")
    fun updateProduct(
        @PathVariable productId: Long,
        @RequestBody request: ProductUpdateRequest
    ): ResponseEntity<Any> {
        productFacadeService.updateProduct(productId, request.toCommand())
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}")
    fun removeProduct(@PathVariable productId: Long): ResponseEntity<Any> {
        productFacadeService.removeProduct(productId)
        return ResponseEntity.noContent().build();
    }
}
