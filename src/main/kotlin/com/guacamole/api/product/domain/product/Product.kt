package com.guacamole.api.product.domain.product

import com.guacamole.api.common.entity.BaseTimeEntity
import com.guacamole.api.common.extension.greaterOrEqual
import javax.persistence.*

@Table(name = "product")
@Entity
class Product(

    @Column(name = "category_id")
    var categoryId: Long,

    @Column(name = "name")
    var name: String,

    @Column(name = "description_image_path")
    var descriptionImagePath: String,

    @Column(name = "origin_place")
    var originPlace: String,

    @Column(name = "detail_description")
    var detailDescription: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) : BaseTimeEntity() {
    init {
        require(categoryId.greaterOrEqual(0))
        require(name.isNotBlank())
    }

    fun update(
        categoryId: Long,
        name: String,
        descriptionImagePath: String,
        originPlace: String,
        detailDescription: String
    ): Product =
        Product(categoryId, name, detailDescription, originPlace, detailDescription, this.id)
}
