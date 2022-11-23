package com.guacamole.api.product.domain.command

import com.guacamole.api.common.extension.greaterOrEqual
import com.guacamole.api.product.domain.Category

class CategoryCommand(
    val name: String,
    val parentId: Long
) {
    init {
        require(name.isNotBlank())
        require(parentId.greaterOrEqual(0))
    }

    fun toCategory(): Category = Category(name, parentId)
}
