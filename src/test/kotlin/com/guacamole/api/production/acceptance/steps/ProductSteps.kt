package com.guacamole.api.production.acceptance.steps

import com.guacamole.api.production.fixture.*
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.AssertionsForInterfaceTypes
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

object ProductSteps {

    /*
    - 상품 정보 등록 시 상품 명이 등록되어야 한다.
    - 상품 정보 등록 시 대표 썸네일 이미지가 등록되어야 한다.
    - 상품 정보 등록 시 상세설명 이미지가 등록되어야 한다.
    - 상품 정보 등록 시 상세 정보가 등록되어야 한다.
        - 상세 정보에는 재질이 포함될 수 있다.
        - 상세 정보에는 원산지가 포함될 수 있다.
    - 상품 정보 등록 시 카테고리가 선택되어야 한다.
     */
    fun `상품 생성 요청을 한다`(
        categoryId: Long = PRODUCT_CATEGORY_ID,
        name: String = PRODUCT_NAME,
        descriptionImagePath: String = PRODUCT_DESCRIPTION_IMAGE_PATH,
        originPlace: String = PRODUCT_ORIGIN_PLACE,
        detailDescription: String = PRODUCT_DETAIL_DESCRIPTION,
    ): ExtractableResponse<Response> {
        val params = `상품 생성 데이터를 만든다`(categoryId, name, descriptionImagePath, originPlace, detailDescription)
        return RestAssured.given().log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(ContentType.JSON)
            .body(params)
            .`when`().post(PRODUCT_PATH)
            .then().log().all()
            .extract()
    }

    fun `상품 수정 요청을 한다`(
        id: Long = 0,
        categoryId: Long = PRODUCT_CATEGORY_ID,
        name: String = PRODUCT_NAME,
        descriptionImagePath: String = PRODUCT_DESCRIPTION_IMAGE_PATH,
        originPlace: String = PRODUCT_ORIGIN_PLACE,
        detailDescription: String = PRODUCT_DETAIL_DESCRIPTION,
    ): ExtractableResponse<Response> {
        val params = `상품 수정 데이터를 만든다`(categoryId, name, descriptionImagePath, originPlace, detailDescription)
        return RestAssured.given().log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(ContentType.JSON)
            .body(params)
            .`when`().put(PRODUCT_PATH + "/${id}")
            .then().log().all()
            .extract()
    }

    fun `상품 삭제 요청을 한다`(id: Long): ExtractableResponse<Response> {
        return RestAssured.given().log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(ContentType.JSON)
            .`when`().delete(PRODUCT_PATH + "/${id}")
            .then().log().all()
            .extract()
    }

    private fun `상품 수정 데이터를 만든다`(
        categoryId: Long,
        name: String,
        descriptionImagePath: String,
        originPlace: String,
        detailDescription: String
    ): Map<String, String> {
        return mapOf(
            PRODUCT_CATEGORY_ID_FIELD to categoryId.toString(),
            PRODUCT_NAME_FIELD to name,
            PRODUCT_DESCRIPTION_IMAGE_PATH_FIELD to descriptionImagePath,
            PRODUCT_ORIGIN_PLACE_FIELD to originPlace,
            PRODUCT_DETAIL_DESCRIPTION_FIELD to detailDescription
        )
    }

    private fun `상품 생성 데이터를 만든다`(
        categoryId: Long,
        name: String,
        descriptionImagePath: String,
        originPlace: String,
        detailDescription: String
    ): Map<String, String> {
        return `상품 생성 데이터를 만든다`(categoryId.toString(), name, descriptionImagePath, originPlace, detailDescription)
    }

    private fun `상품 생성 데이터를 만든다`(
        categoryId: String,
        name: String,
        descriptionImagePath: String,
        originPlace: String,
        detailDescription: String
    ): Map<String, String> {
        return mapOf(
            PRODUCT_CATEGORY_ID_FIELD to categoryId,
            PRODUCT_NAME_FIELD to name,
            PRODUCT_DESCRIPTION_IMAGE_PATH_FIELD to descriptionImagePath,
            PRODUCT_ORIGIN_PLACE_FIELD to originPlace,
            PRODUCT_DETAIL_DESCRIPTION_FIELD to detailDescription
        )
    }

    fun `상품 생성 요청 됨`(
        categoryId: Long = PRODUCT_CATEGORY_ID,
        name: String = PRODUCT_NAME,
        descriptionImagePath: String = PRODUCT_DESCRIPTION_IMAGE_PATH,
        originPlace: String = PRODUCT_ORIGIN_PLACE,
        detailDescription: String = PRODUCT_DETAIL_DESCRIPTION,
    ): Long {
        val response = `상품 생성 요청을 한다`(categoryId, name, descriptionImagePath, originPlace, detailDescription)
        val header = response.header("location")
        val removePrefix = header.removePrefix(PRODUCT_PATH).removePrefix("/")
        return removePrefix.toLong()
    }

    fun `상품 생성됨`(response: ExtractableResponse<Response>) {
        AssertionsForInterfaceTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    fun `상품 수정됨`(response: ExtractableResponse<Response>) {
        AssertionsForInterfaceTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    fun `상품 삭제됨`(response: ExtractableResponse<Response>) {
        AssertionsForInterfaceTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
