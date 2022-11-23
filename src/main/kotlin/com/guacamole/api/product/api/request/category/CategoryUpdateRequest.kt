package com.guacamole.api.product.api.request.category

import com.guacamole.api.product.application.command.CategoryCommand
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CategoryUpdateRequest(
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
