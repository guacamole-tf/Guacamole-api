package com.guacamole.api.production.domain.product

class Category(
    var name: String,
    var parentId: Long? = null,
    var id: Long? = null
) {
}
