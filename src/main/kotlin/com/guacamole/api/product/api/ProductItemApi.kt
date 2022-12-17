package com.guacamole.api.product.api

import com.guacamole.api.product.api.request.productitem.ProductItemCreateRequest
import com.guacamole.api.product.api.request.productitem.ProductItemUpdateRequest
import com.guacamole.api.product.application.ProductItemFacadeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RequestMapping("/api/products/{productId}/items")
@RestController
class ProductItemApi(
    private val productItemFacadeService: ProductItemFacadeService
) {
    @PostMapping
    fun createProductItem(
        @PathVariable productId: Long,
        @RequestBody @Valid productItemCreateRequest: ProductItemCreateRequest
    ): ResponseEntity<URI> {
        val productItemId =
            productItemFacadeService.registrationProductItem(productId, productItemCreateRequest.toCommand())
        return ResponseEntity.created(URI.create("/api/products/$productId/items/$productItemId")).build()
    }

    @PutMapping("/{productItemId}")
    fun updateProductItem(
        @PathVariable productId: Long,
        @PathVariable productItemId: Long,
        @RequestBody @Valid productItemUpdateRequest: ProductItemUpdateRequest
    ): ResponseEntity<UInt> {
        productItemFacadeService.updateProductItem(productId, productItemId, productItemUpdateRequest.toCommand())
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{productItemId}")
    fun deleteProductItem(
        @PathVariable productId: Long,
        @PathVariable productItemId: Long,
    ): ResponseEntity<UInt> {
        productItemFacadeService.deleteProductItem(productId, productItemId)
        return ResponseEntity.noContent().build()
    }
}
