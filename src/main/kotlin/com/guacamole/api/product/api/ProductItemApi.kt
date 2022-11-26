package com.guacamole.api.product.api

import com.guacamole.api.product.api.request.productitem.ProductItemCreateRequest
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
    ): ResponseEntity<Any> {
        val productItemId = productItemFacadeService.registrationProductItem(productItemCreateRequest.toCommand())
        return ResponseEntity.created(URI.create("/api/products/${productId}/items/${productItemId}")).build()
    }
}
