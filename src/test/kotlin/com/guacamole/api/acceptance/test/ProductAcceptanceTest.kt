package com.guacamole.api.acceptance.test

import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 생성 요청 됨`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 생성됨`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 식별 값`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리를 생성 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 삭제 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 삭제됨`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 생성 요청 됨`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 생성 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 생성됨`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 수정 요청을 한다`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 수정됨`
import com.guacamole.api.acceptance.steps.ProductSteps.`상품 식별 값`
import com.guacamole.api.fixture.CATEGORY_NAME
import com.guacamole.api.fixture.CATEGORY_OTHER_NAME
import com.guacamole.api.fixture.CATEGORY_ROOT_PARENT_ID
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("상품 인수 테스트")
class ProductAcceptanceTest : AcceptanceTest() {

    @Test
    fun `상품을 관리한다`() {
        val categoryCreateResponse = `카테고리를 생성 요청을 한다`()
        `카테고리 생성됨`(categoryCreateResponse)

        val categoryId = `카테고리 식별 값`(categoryCreateResponse)

        val productCreateResponse = `상품 생성 요청을 한다`(categoryId = categoryId)
        `상품 생성됨`(productCreateResponse)

        val productId = `상품 식별 값`(productCreateResponse)

        val productUpdateResponse = `상품 수정 요청을 한다`(id = productId)
        `상품 수정됨`(productUpdateResponse)

        val productDeleteResponse = `상품 삭제 요청을 한다`(id = productId)
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
