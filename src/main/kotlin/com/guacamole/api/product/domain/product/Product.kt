package com.guacamole.api.product.domain.product

import com.guacamole.api.common.entity.BaseTimeEntity
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
    fun update(
        categoryId: Long,
        name: String,
        descriptionImagePath: String,
        originPlace: String,
        detailDescription: String
    ) {
        this.categoryId = categoryId
        this.name = name
        this.descriptionImagePath = descriptionImagePath
        this.originPlace = originPlace
        this.detailDescription = detailDescription
    }
}
