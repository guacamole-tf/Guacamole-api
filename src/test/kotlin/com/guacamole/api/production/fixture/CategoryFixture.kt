package com.guacamole.api.production.fixture

import com.guacamole.api.production.product.domain.Category

const val CATEGORY_PATH = "/api/categories"

const val CATEGORY_NAME_FIELD = "name"
const val CATEGORY_PARENT_ID_FIELD = "parentId"

const val CATEGORY_NAME: String = "패션"
const val CATEGORY_PARENT_ID: Long = 0

const val OTHER_CATEGORY_NAME = "다른 이름으로 변경"


fun createCategory(
    name: String = CATEGORY_NAME,
    parentId: Long = CATEGORY_PARENT_ID
): Category {
    return Category(name, parentId)
}
