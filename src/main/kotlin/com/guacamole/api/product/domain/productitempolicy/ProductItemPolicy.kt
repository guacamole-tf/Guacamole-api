package com.guacamole.api.product.domain.productitempolicy

import com.guacamole.api.common.entity.BaseTimeEntity
import javax.persistence.*

@Table(name = "product_item_policy")
@Entity
class ProductItemPolicy(
    @Column(name = "product_item_id")
    var productItemId: Long,

    @Column(name = "limit_unit")
    var limitUnit: String,

    @Column(name = "limit_count")
    var limitCount: Int,

    @Column(name = "banned_at")
    var bannedAt: Boolean,

    @Column(name = "discount_unit")
    var discountUnit: String,

    @Column(name = "discount_rate")
    var discountRate: Int,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) : BaseTimeEntity() {
}
