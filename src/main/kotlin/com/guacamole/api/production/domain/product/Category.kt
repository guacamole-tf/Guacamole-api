package com.guacamole.api.production.domain.product

class Category(
    var parent_id: Long,
    var name: String,
    var id: Long? = null
) {
}