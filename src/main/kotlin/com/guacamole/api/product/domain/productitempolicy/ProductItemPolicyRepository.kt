package com.guacamole.api.product.domain.productitempolicy

import org.springframework.data.jpa.repository.JpaRepository

interface ProductItemPolicyRepository : JpaRepository<ProductItemPolicy, Long> {
}