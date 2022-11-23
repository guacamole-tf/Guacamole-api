package com.guacamole.api.product.domain.productitempolicy

import org.springframework.stereotype.Service

@Service
class ProductItemPolicyService(
    private val productItemPolicyStore: ProductItemPolicyStore = ProductItemPolicyMapStore()
) {
    fun saveAndFlush(productItemPolicy: ProductItemPolicy): ProductItemPolicy =
        productItemPolicyStore.saveAndFlush(productItemPolicy)
}