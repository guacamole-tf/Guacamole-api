package com.guacamole.api.production.product.api.request.category

import com.guacamole.api.production.product.domain.command.CategoryCommand
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class CategoryUpdateRequest(
    @field:NotBlank
    val name: String,

    @field:Min(0)
    @field:NotNull
    val parentId: Long
) {
    fun toCommand(): CategoryCommand {
        return CategoryCommand(name, parentId)
    }
}
