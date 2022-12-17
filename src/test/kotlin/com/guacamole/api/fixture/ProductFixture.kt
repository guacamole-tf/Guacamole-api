package com.guacamole.api.fixture

import com.guacamole.api.product.domain.product.Product

const val PRODUCT_PATH = "/api/products"

const val PRODUCT_CATEGORY_ID_FIELD = "categoryId"
const val PRODUCT_NAME_FIELD = "name"
const val PRODUCT_DESCRIPTION_IMAGE_PATH_FIELD = "descriptionImagePath"
const val PRODUCT_ORIGIN_PLACE_FIELD = "originPlace"
const val PRODUCT_DETAIL_DESCRIPTION_FIELD = "detailDescription"
const val PRODUCT_ID_FIELD = "id"

const val PRODUCT_ID: Long = 1L
const val PRODUCT_CATEGORY_ID: Long = 1L
const val PRODUCT_NAME: String = "상품"
const val PRODUCT_DESCRIPTION_IMAGE_PATH: String = "http://gaucamoletf.com/images/sampleDescriptionImage.png"
const val PRODUCT_ORIGIN_PLACE: String = "원산지"
const val PRODUCT_DETAIL_DESCRIPTION: String = "과카몰리 상품"

fun createProduct(
    categoryId: Long = PRODUCT_CATEGORY_ID,
    name: String = PRODUCT_NAME,
    descriptionImagePath: String = PRODUCT_DESCRIPTION_IMAGE_PATH,
    originPlace: String = PRODUCT_ORIGIN_PLACE,
    detailDescription: String = PRODUCT_DETAIL_DESCRIPTION,
    id: Long = PRODUCT_ID,
): Product {
    return Product(
        categoryId, name, descriptionImagePath, originPlace, detailDescription, id
    )
}
