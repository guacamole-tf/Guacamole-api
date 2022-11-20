package com.guacamole.api.production.acceptance.steps

import com.guacamole.api.production.fixture.CATEGORY_NAME
import com.guacamole.api.production.fixture.CATEGORY_NAME_FIELD
import com.guacamole.api.production.fixture.CATEGORY_PARENT_ID
import com.guacamole.api.production.fixture.CATEGORY_PARENT_ID_FIELD
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


object CategorySteps {

    private const val CATEGORY_PATH = "/api/categories"

    fun `카테고리를 생성 요청을 한다`(
        name: String = CATEGORY_NAME,
        parentId: Long = CATEGORY_PARENT_ID
    ): ExtractableResponse<Response> {
        val params: Map<String, String> = `카테고리 생성 데이터를 만든다`(name, parentId)
        return RestAssured.given().log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(ContentType.JSON)
            .body(params)
            .`when`().post(CATEGORY_PATH)
            .then().log().all()
            .extract()
    }

    private fun `카테고리 생성 데이터를 만든다`(name: String, parentId: Long): Map<String, String> {
        return `카테고리 생성 데이터를 만든다`(name, parentId.toString())
    }

    private fun `카테고리 생성 데이터를 만든다`(name: String, parentId: String): Map<String, String> {
        return mapOf(CATEGORY_NAME_FIELD to name, CATEGORY_PARENT_ID_FIELD to parentId)
    }

    fun `카테고리 생성됨`(response: ExtractableResponse<Response>) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
