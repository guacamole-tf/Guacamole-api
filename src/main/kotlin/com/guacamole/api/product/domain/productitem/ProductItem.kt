package com.guacamole.api.product.domain.productitem

import com.guacamole.api.common.entity.BaseTimeEntity
import com.guacamole.api.common.extension.greaterOrEqual
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

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
    init {
        require(productId.greaterOrEqual(0))
        require(stockId.greaterOrEqual(0))
    }

    fun update(
        thumbnailImagePath: String,
        sizeUnit: String,
        sizeRate: Int,
        price: Int,
    ): ProductItem =
        ProductItem(this.productId, this.stockId, thumbnailImagePath, sizeUnit, sizeRate, price, this.id)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductItem

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
