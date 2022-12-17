package com.guacamole.api.acceptance.test

import com.guacamole.api.acceptance.steps.CategorySteps
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 생성 요청 됨`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 생성됨`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리를 생성 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 삭제 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 삭제됨`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 생성 요청 됨`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 생성 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 생성됨`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 수정 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 수정됨`
import com.guacamole.api.acceptance.steps.ProductItemSteps.`상품 아이템 식별 값`
import com.guacamole.api.acceptance.steps.ProductSteps
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 생성 요청 됨`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 생성 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 생성됨`
import com.guacamole.api.fixture.CATEGORY_NAME
import com.guacamole.api.fixture.CATEGORY_ROOT_PARENT_ID
import com.guacamole.api.fixture.PRODUCT_ITEM_COUNT
import com.guacamole.api.fixture.PRODUCT_ITEM_OTHER_COUNT
import com.guacamole.api.fixture.PRODUCT_ITEM_OTHER_PRICE
import com.guacamole.api.fixture.PRODUCT_ITEM_OTHER_SIZE_UNIT
import com.guacamole.api.fixture.PRODUCT_ITEM_OTHER_THUMBNAIL_IMAGE_PATH
import com.guacamole.api.fixture.PRODUCT_ITEM_PRICE
import com.guacamole.api.fixture.PRODUCT_ITEM_SIZE_UNIT
import com.guacamole.api.fixture.PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH
import com.guacamole.api.fixture.PRODUCT_SIZE_OTHER_RATE
import com.guacamole.api.fixture.PRODUCT_SIZE_RATE
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("상품 아이템 인수 테스트")
class ProductionItemAcceptanceTest : AcceptanceTest() {

    @Test
    fun `상품 아이템을 관리한다`() {
        val categoryCreateResponse = `카테고리를 생성 요청을 한다`()
        `카테고리 생성됨`(categoryCreateResponse)
        val categoryId = CategorySteps.`카테고리 식별 값`(categoryCreateResponse)

        val productCreateResponse = `상품 생성 요청을 한다`(categoryId = categoryId)
        `상품 생성됨`(productCreateResponse)

        val productId = ProductSteps.`상품 식별 값`(productCreateResponse)

        val response = `상품 아이템 생성 요청을 한다`(productId = productId)
        `상품 아이템 생성됨`(response)

        val productItemId = `상품 아이템 식별 값`(productId, response)

        val productItemUpdateResponse = `상품 아이템 수정 요청을 한다`(
            productId = productId,
            productItemId = productItemId
        )
        `상품 아이템 수정됨`(productItemUpdateResponse)

        val deleteResponse = `상품 아이템 삭제 요청을 한다`(productId = productId, productItemId = productItemId)
        `상품 아이템 삭제됨`(deleteResponse)
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

        val response = `상품 아이템 수정 요청을 한다`(
            productId,
            PRODUCT_ITEM_OTHER_PRICE,
            PRODUCT_ITEM_OTHER_COUNT,
            PRODUCT_ITEM_OTHER_THUMBNAIL_IMAGE_PATH,
            PRODUCT_ITEM_OTHER_SIZE_UNIT,
            PRODUCT_SIZE_OTHER_RATE,
            productItemId
        )

        `상품 아이템 수정됨`(response)
    }

    @Test
    fun `상품 아이템을 삭제한다`() {
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

        val response = `상품 아이템 삭제 요청을 한다`(productItemId, productId)
        `상품 아이템 삭제됨`(response)
    }
}
