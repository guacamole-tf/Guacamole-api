package com.guacamole.api.production.product.domain

class Product(
    var categoryId: Long,
    var name: String,
    var thumbnailImagePath: String,
    var descriptionImagePath: String,
    var originPlace: String,
    var detailDescription: String,
    var id: Long? = null
) {
}