package com.guacamole.api.product.domain.productitem

import com.guacamole.api.common.entity.BaseTimeEntity
import javax.persistence.*

@Table(name = "product_item")
@Entity
class ProductItem(

    @Column(name = "product_id")
    var productId: Long,

    @Column(name = "stock_id")
    var stockId: Long,

    @Column(name = "thumbnail_image_path")
    var thumbnailImagePath: String,

    @Column(name = "size_unit")
    var sizeUnit: String,

    @Column(name = "size_rate")
    var sizeRate: Int,

    @Column(name = "price")
    var price: Int,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) : BaseTimeEntity() {
}
