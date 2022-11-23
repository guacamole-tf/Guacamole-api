package com.guacamole.api.acceptance

import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 생성 요청 됨`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 생성됨`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리를 생성 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 삭제 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 삭제됨`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 생성 요청 됨`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 생성 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 생성됨`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 수정 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 수정됨`
import com.guacamole.api.fixture.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("상품 인수 테스트")
class ProductAcceptanceTest : AcceptanceTest() {

    /**
     * Scenario: 삼품 관리
     * When 카테고리 생성 요청
     * Then 카테고리 생성됨
     * When 상품 생성 요청
     * Then 상품 생성됨
     * When 상품 수정 요청
     * Then 상품 수정됨
     * When 상품 삭제 요청
     * Then 상품 삭제됨
     */
    @Test
    fun `상품을 관리한다`() {
        val categoryCreateResponse = `카테고리를 생성 요청을 한다`(CATEGORY_NAME, CATEGORY_ROOT_PARENT_ID)
        `카테고리 생성됨`(categoryCreateResponse)

        val productCreateResponse = `상품 생성 요청을 한다`(CATEGORY_ID)
        `상품 생성됨`(productCreateResponse)

        val productUpdateResponse = `상품 수정 요청을 한다`(CATEGORY_ID)
        `상품 수정됨`(productUpdateResponse)

        val productDeleteResponse = `상품 삭제 요청을 한다`(PRODUCT_ID)
        `상품 수정됨`(productDeleteResponse)
    }

    @Test
    fun `상품을 생성한다`() {
        val categoryId = `카테고리 생성 요청 됨`(CATEGORY_NAME, CATEGORY_ROOT_PARENT_ID)

        val response = `상품 생성 요청을 한다`(categoryId)

        `상품 생성됨`(response)
    }

    @Test
    fun `상품을 수정한다`() {
        val originalCategoryId = `카테고리 생성 요청 됨`(CATEGORY_NAME, CATEGORY_ROOT_PARENT_ID)

        val productId = `상품 생성 요청 됨`(originalCategoryId)
        val updateCategoryId = `카테고리 생성 요청 됨`(CATEGORY_OTHER_NAME, CATEGORY_ROOT_PARENT_ID)

        val response = `상품 수정 요청을 한다`(categoryId = updateCategoryId, id = productId)
        `상품 수정됨`(response)
    }

    @Test
    fun `상품을 삭제한다`() {
        val originalCategoryId = `카테고리 생성 요청 됨`(CATEGORY_NAME, CATEGORY_ROOT_PARENT_ID)
        val productId = `상품 생성 요청 됨`(originalCategoryId)

        val response = `상품 삭제 요청을 한다`(productId)
        `상품 삭제됨`(response)
    }
}
