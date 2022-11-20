package com.guacamole.api.production.product.domain

class Category(
    var name: String,
    var parentId: Long = DEFAULT_PARENT_ID,
    var id: Long? = null
) {
    init {
        require(name.isNotBlank())
        require(parentId.greaterOrEqual(DEFAULT_PARENT_ID))
    }

    fun update(name: String, parentId: Long) {
        this.name = name
        this.parentId = parentId
    }

    companion object {
        const val DEFAULT_PARENT_ID: Long = 0L
    }
}

fun Long.greaterOrEqual(other: Long): Boolean {
    return this >= other
}
