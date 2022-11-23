package com.guacamole.api.fixture

import com.guacamole.api.product.domain.ProductItem

const val PRODUCT_ITEM_PATH = "/api/products/%s/items"

const val PRODUCT_ITEM_PRODUCT_ID: Long = 1L
const val PRODUCT_ITEM_PRICE: Int = 1000
const val PRODUCT_ITEM_COUNT: Int = 1
const val PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH: String = "http://gaucamoletf.com/images/sampleThumnauilImage.png"
const val PRODUCT_ITEM_SIZE_UNIT: Long = 1L
const val PRODUCT_SIZE_RATE: Int = 1
const val PRODUCT_ITEM_STOCK_ID: Long = 1L
const val PRODUCT_ITEM_ID: Long = 1L

const val PRODUCT_ITEM_PRODUCT_ID_FIELD: String = "productId"
const val PRODUCT_ITEM_PRICE_FIELD: String = "price"
const val PRODUCT_ITEM_COUNT_FIELD: String = "count"
const val PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH_FIELD: String = "thumnailImagePath"
const val PRODUCT_ITEM_SIZE_UNIT_FIELD: String = "sizeUnit"
const val PRODUCT_ITEM_SIZE_RATE_FIELD: String = "sizeRate"

fun createProductItem(
    productId: Long = PRODUCT_ITEM_PRODUCT_ID,
    stockId: Long = PRODUCT_ITEM_STOCK_ID,
    sizeUnit: Long = PRODUCT_ITEM_SIZE_UNIT,
    sizeRate: Int = PRODUCT_SIZE_RATE,
    price: Int = PRODUCT_ITEM_PRICE,
    id: Long = PRODUCT_ITEM_ID
): ProductItem {
    return ProductItem(productId, stockId, sizeUnit, sizeRate, price, id)
}
