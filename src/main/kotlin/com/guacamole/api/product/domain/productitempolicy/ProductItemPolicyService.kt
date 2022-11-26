package com.guacamole.api.product.domain.productitempolicy

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ProductItemPolicyService(
    private val productItemPolicyRepository: ProductItemPolicyRepository
) {
    fun saveAndFlush(productItemPolicy: ProductItemPolicy): ProductItemPolicy =
        productItemPolicyRepository.saveAndFlush(productItemPolicy)
}
