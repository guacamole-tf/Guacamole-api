package com.guacamole.api.product.domain.productitempolicy

interface ProductItemPolicyStore {
    fun saveAndFlush(productItemPolicy: ProductItemPolicy): ProductItemPolicy
    fun findById(productItemPolicyId: Long): ProductItemPolicy
    fun remove(productItemPolicyId: Long)
    fun existsById(productItemPolicyId: Long): Boolean
}
