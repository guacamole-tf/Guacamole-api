package com.guacamole.api.product.domain.productitem

import org.springframework.data.jpa.repository.JpaRepository

interface ProductItemRepository : JpaRepository<ProductItem, Long> {
}