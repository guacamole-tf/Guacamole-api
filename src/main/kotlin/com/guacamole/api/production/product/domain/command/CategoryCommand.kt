package com.guacamole.api.production.product.domain.command

import com.guacamole.api.production.product.domain.Category
import com.guacamole.api.production.product.domain.greaterOrEqual

class CategoryCommand(
    val name: String,
    val parentId: Long
) {
    init {
        require(name.isNotBlank())
        require(parentId.greaterOrEqual(0))
    }


    fun toEntity(): Category = Category(name, parentId)
}
