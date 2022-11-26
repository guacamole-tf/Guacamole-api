package com.guacamole.api.acceptance

import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 생성 요청 됨`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 생성됨`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리를 생성 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 생성 요청 됨`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 생성 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 생성됨`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 수정 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 수정됨`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 생성 요청 됨`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 생성 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 생성됨`
import com.guacamole.api.fixture.*
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("상품 아이템 인수 테스트")
class ProductionItemAcceptanceTest : AcceptanceTest() {

    @Test
    fun `상품 아이템을 관리한다`() {
        val categoryCreateResponse = `카테고리를 생성 요청을 한다`(CATEGORY_NAME, CATEGORY_ROOT_PARENT_ID)
        `카테고리 생성됨`(categoryCreateResponse)

        val productCreateResponse = `상품 생성 요청을 한다`(CATEGORY_ID)
        `상품 생성됨`(productCreateResponse)

    }

    @Test
    fun `상품 아이템을 생성한다`() {
        val categoryId = `카테고리 생성 요청 됨`(CATEGORY_NAME, CATEGORY_ROOT_PARENT_ID)
        val productId = `상품 생성 요청 됨`(categoryId)

        val response = `상품 아이템 생성 요청을 한다`(
            productId,
            PRODUCT_ITEM_PRICE,
            PRODUCT_ITEM_COUNT,
            PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH,
            PRODUCT_ITEM_SIZE_UNIT,
            PRODUCT_SIZE_RATE
        )

        `상품 아이템 생성됨`(response)
    }

    @Test
    fun `상품 아이템을 수정한다`() {
        val categoryId = `카테고리 생성 요청 됨`(CATEGORY_NAME, CATEGORY_ROOT_PARENT_ID)
        val productId = `상품 생성 요청 됨`(categoryId)

        val productItemId = `상품 아이템 생성 요청 됨`(
            productId,
            PRODUCT_ITEM_PRICE,
            PRODUCT_ITEM_COUNT,
            PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH,
            PRODUCT_ITEM_SIZE_UNIT,
            PRODUCT_SIZE_RATE
        )

        val response = `상품 아이템 수정 요청을 한다`(productItemId,
            productId,
            PRODUCT_ITEM_PRICE,
            PRODUCT_ITEM_COUNT,
            PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH,
            PRODUCT_ITEM_SIZE_UNIT,
            PRODUCT_SIZE_RATE
        )

        `상품 아이템 수정됨`(response)
    }
}
