package com.guacamole.api.acceptance.test

import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 삭제됨`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 생성 요청 됨`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 생성됨`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 수정됨`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리 식별 값`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리를 삭제 요청을 한다`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리를 생성 요청을 한다`
import com.guacamole.api.acceptance.steps.CategorySteps.`카테고리를 수정 요청을 한다`
import com.guacamole.api.fixture.CATEGORY_NAME
import com.guacamole.api.fixture.CATEGORY_OTHER_NAME
import com.guacamole.api.fixture.CATEGORY_ROOT_PARENT_ID
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("상품 카테고리 생성 인수 테스트")
class CategoryAcceptanceTest : AcceptanceTest() {

    @Test
    fun `카테고리를 관리한다`() {
        val categoryCreateResponse = `카테고리를 생성 요청을 한다`()
        `카테고리 생성됨`(categoryCreateResponse)

        val categoryId = `카테고리 식별 값`(categoryCreateResponse)

        val categoryUpdateResponse = `카테고리를 수정 요청을 한다`(categoryId = categoryId)
        `카테고리 수정됨`(categoryUpdateResponse)

        val categoryDeleteResponse = `카테고리를 삭제 요청을 한다`(categoryId = categoryId)
        `카테고리 삭제됨`(categoryDeleteResponse)
    }

    @Test
    fun `카테고리를 생성한다`() {
        val response = `카테고리를 생성 요청을 한다`(CATEGORY_NAME, CATEGORY_ROOT_PARENT_ID)
        `카테고리 생성됨`(response)
    }

    @Test
    fun `카테고리를 수정한다`() {
        var createCategoryId = `카테고리 생성 요청 됨`(CATEGORY_NAME, CATEGORY_ROOT_PARENT_ID)
        val response = `카테고리를 수정 요청을 한다`(CATEGORY_OTHER_NAME, CATEGORY_ROOT_PARENT_ID, createCategoryId)

        `카테고리 수정됨`(response)
    }

    @Test
    fun `카테고리를 삭제한다`() {
        var createCategoryId = `카테고리 생성 요청 됨`(CATEGORY_NAME, CATEGORY_ROOT_PARENT_ID)
        val response = `카테고리를 삭제 요청을 한다`(createCategoryId)

        `카테고리 삭제됨`(response)
    }
}
