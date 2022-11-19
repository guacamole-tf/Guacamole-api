package com.guacamole.api.production.domain.product

class Category(
    var name: String,
    var parentId: Long = 0,
    var id: Long? = null
) {
    init {
        require(name.isNotBlank())
        require(parentId.get(0))
    }
}

fun Long.get(other: Long): Boolean {
    return this >= other
}
