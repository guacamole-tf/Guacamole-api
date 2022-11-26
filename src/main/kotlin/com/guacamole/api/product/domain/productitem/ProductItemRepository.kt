package com.guacamole.api.product.domain.productitem

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductItemRepository : JpaRepository<ProductItem, Long> {
    @Query("SELECT pi.stockId from ProductItem pi where pi.id = :productItemId")
    fun findStockIdByIdOrNull(productItemId: Long): Long?
}
