package com.guacamole.api.acceptance.steps

import com.guacamole.api.fixture.*
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

object CategorySteps {

    /**
     * Create Request
     */
    fun `카테고리를 생성 요청을 한다`(
        name: String = CATEGORY_NAME,
        parentId: Long = CATEGORY_ROOT_PARENT_ID
    ): ExtractableResponse<Response> {
        val params = `카테고리 생성 데이터를 만든다`(name, parentId)
        return RestAssured.given().log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(ContentType.JSON)
            .body(params)
            .`when`().post(CATEGORY_PATH)
            .then().log().all()
            .extract()
    }

    fun `카테고리를 수정 요청을 한다`(
        name: String = CATEGORY_NAME,
        parentId: Long = CATEGORY_ROOT_PARENT_ID,
        id: Long = CATEGORY_ID
    ): ExtractableResponse<Response> {
        val params = `카테고리 수정 데이터를 만든다`(name, parentId)
        return RestAssured.given().log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(ContentType.JSON)
            .body(params)
            .`when`().put(CATEGORY_PATH + "/${id}")
            .then().log().all()
            .extract()
    }

    fun `카테고리를 삭제 요청을 한다`(
        id: Long = CATEGORY_ID
    ): ExtractableResponse<Response> {
        return RestAssured.given().log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(ContentType.JSON)
            .`when`().delete(CATEGORY_PATH + "/${id}")
            .then().log().all()
            .extract()
    }


    /**
     * Create Request Data
     */
    private fun `카테고리 생성 데이터를 만든다`(
        name: String = CATEGORY_NAME,
        parentId: Long = CATEGORY_ROOT_PARENT_ID
    ): Map<String, String> =
        mapOf(CATEGORY_NAME_FIELD to name, CATEGORY_PARENT_ID_FIELD to parentId.toString())

    private fun `카테고리 수정 데이터를 만든다`(
        name: String = CATEGORY_NAME,
        parentId: Long = CATEGORY_ROOT_PARENT_ID
    ): Map<String, String> =
        mapOf(CATEGORY_NAME_FIELD to name, CATEGORY_PARENT_ID_FIELD to parentId.toString())

    /**
     * Response Parse Data
     */
    fun `카테고리 생성 요청 됨`(
        categoryName: String = CATEGORY_NAME,
        categoryParentId: Long = CATEGORY_ROOT_PARENT_ID
    ): Long {
        val response = `카테고리를 생성 요청을 한다`(categoryName, categoryParentId)
        val header = response.header("location")
        val removePrefix = header.removePrefix(CATEGORY_PATH).removePrefix("/")
        return removePrefix.toLong()
    }

    /**
     * Response Assertion
     */
    fun `카테고리 생성됨`(response: ExtractableResponse<Response>) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    fun `카테고리 수정됨`(response: ExtractableResponse<Response>) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    fun `카테고리 삭제됨`(response: ExtractableResponse<Response>) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
