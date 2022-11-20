package com.guacamole.api.production.acceptance

import com.guacamole.api.production.acceptance.steps.CategorySteps.`카테고리 생성됨`
import com.guacamole.api.production.acceptance.steps.CategorySteps.`카테고리 수정됨`
import com.guacamole.api.production.acceptance.steps.CategorySteps.`카테고리를 생성 요청됨`
import com.guacamole.api.production.acceptance.steps.CategorySteps.`카테고리를 생성 요청을 한다`
import com.guacamole.api.production.acceptance.steps.CategorySteps.`카테고리를 수정 요청을 한다`
import com.guacamole.api.production.fixture.CATEGORY_NAME
import com.guacamole.api.production.fixture.CATEGORY_PARENT_ID
import com.guacamole.api.production.fixture.OTHER_CATEGORY_NAME
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("상품 카테고리 생성 인수 테스트")
class CategoryAcceptanceTest : AcceptanceTest() {

    /**
     * Scenario: 카테고리를 관리
     * When 카테고리 생성 요청
     * Then 카테고리 생성됨
     * When 카테고리 수정 요청
     * Then 카테고리 수정됨
     * When 카테고리 삭제 요청
     * Then 카테고리 삭제됨
     */
    @Test
    fun `카테고리를 관리한다`() {
        val categoryCreateResponse = `카테고리를 생성 요청을 한다`(CATEGORY_NAME, CATEGORY_PARENT_ID)
        `카테고리 생성됨`(categoryCreateResponse)

        val categoryUpdateResponse = `카테고리를 수정 요청을 한다`(CATEGORY_NAME, CATEGORY_PARENT_ID)
        `카테고리 수정됨`(categoryUpdateResponse)
    }

    @Test
    fun `카테고리를 생성한다`() {
        val response = `카테고리를 생성 요청을 한다`(CATEGORY_NAME, CATEGORY_PARENT_ID)

        `카테고리 생성됨`(response)
    }

    @Test
    fun `카테고리를 수정한다`() {
        var createCategoryId = `카테고리를 생성 요청됨`(CATEGORY_NAME, CATEGORY_PARENT_ID)
        val response = `카테고리를 수정 요청을 한다`(OTHER_CATEGORY_NAME, CATEGORY_PARENT_ID, createCategoryId)

        `카테고리 수정됨`(response)
    }
}
