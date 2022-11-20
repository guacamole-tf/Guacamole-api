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

    companion object {
        const val DEFAULT_PARENT_ID: Long = 0L
    }
}

private fun Long.greaterOrEqual(other: Long): Boolean {
    return this >= other
}
