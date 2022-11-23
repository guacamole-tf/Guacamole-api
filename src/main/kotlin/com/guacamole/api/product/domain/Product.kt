package com.guacamole.api.product.domain

class Product(
    var categoryId: Long,
    var name: String,
    var descriptionImagePath: String,
    var originPlace: String,
    var detailDescription: String,
    var id: Long? = null
) {
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
