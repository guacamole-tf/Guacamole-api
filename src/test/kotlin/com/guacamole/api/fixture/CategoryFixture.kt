package com.guacamole.api.fixture

import com.guacamole.api.product.domain.category.Category

const val CATEGORY_PATH = "/api/categories"

const val CATEGORY_NAME_FIELD = "name"
const val CATEGORY_PARENT_ID_FIELD = "parentId"

const val CATEGORY_NAME: String = "패션"
const val CATEGORY_ROOT_PARENT_ID: Long = 0
const val CATEGORY_ID: Long = 1

const val CATEGORY_OTHER_NAME: String = "잡화"
const val CATEGORY_OTHER_PARENT_ID: Long = 1
const val CATEGORY_OTHER_ID: Long = 2

fun createCategory(
    name: String = CATEGORY_NAME,
    parentId: Long = CATEGORY_ROOT_PARENT_ID
): Category {
    return Category(name, parentId)
}
