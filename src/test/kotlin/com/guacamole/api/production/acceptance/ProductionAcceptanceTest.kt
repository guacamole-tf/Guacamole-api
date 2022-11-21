package com.guacamole.api.production.acceptance

import com.guacamole.api.production.acceptance.steps.CategorySteps
import com.guacamole.api.production.acceptance.steps.CategorySteps.`카테고리 생성됨`
import com.guacamole.api.production.acceptance.steps.CategorySteps.`카테고리를 생성 요청됨`
import com.guacamole.api.production.acceptance.steps.ProductSteps.`상품 생성 요청을 한다`
import com.guacamole.api.production.acceptance.steps.ProductSteps.`상품 생성됨`
import com.guacamole.api.production.fixture.CATEGORY_NAME
import com.guacamole.api.production.fixture.CATEGORY_PARENT_ID
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("상품 인수 테스트")
class ProductionAcceptanceTest {

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
        val categoryCreateResponse = CategorySteps.`카테고리를 생성 요청을 한다`(CATEGORY_NAME, CATEGORY_PARENT_ID)
        `카테고리 생성됨`(categoryCreateResponse)
    }

    @Test
    fun `상품을 생성한다`() {
        val categoryId = `카테고리를 생성 요청됨`(CATEGORY_NAME, CATEGORY_PARENT_ID)

        val response = `상품 생성 요청을 한다`(categoryId)

        `상품 생성됨`(response)
    }
}
