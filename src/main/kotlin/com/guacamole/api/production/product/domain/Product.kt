package com.guacamole.api.production.product.domain

class Product(
    var category_id: Long,
    var name: String,
    var thumbnail_image_path: String,
    var description_image_path: String,
    var origin_place: String,
    var detail_description: String,
    var id: Long? = null
) {
}