package com.guacamole.api.acceptance.steps

import com.guacamole.api.fixture.*
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.AssertionsForInterfaceTypes
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

object ProductItemSteps {

    /**
     * Create Request
     */
    fun `상품 아이템 생성 요청을 한다`(
        productId: Long = PRODUCT_ITEM_PRODUCT_ID,
        price: Int = PRODUCT_ITEM_PRICE,
        count: Int = PRODUCT_ITEM_COUNT,
        thumbnailImagePath: String = PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH,
        sizeUnit: String = PRODUCT_ITEM_SIZE_UNIT,
        sizeRate: Int = PRODUCT_SIZE_RATE
    ): ExtractableResponse<Response> {
        val params = `상품 생성 데이터를 만든다`(productId, price, count, thumbnailImagePath, sizeUnit, sizeRate)
        return RestAssured.given().log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(ContentType.JSON)
            .body(params)
            .`when`().post(PRODUCT_ITEM_PATH.format(productId))
            .then().log().all()
            .extract()
    }

    fun `상품 아이템 수정 요청을 한다`(
        productItemId: Long = PRODUCT_ITEM_ID,
        productId: Long = PRODUCT_ITEM_PRODUCT_ID,
        price: Int = PRODUCT_ITEM_PRICE,
        count: Int = PRODUCT_ITEM_COUNT,
        thumbnailImagePath: String = PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH,
        sizeUnit: String = PRODUCT_ITEM_SIZE_UNIT,
        sizeRate: Int = PRODUCT_SIZE_RATE
    ): ExtractableResponse<Response> {
        val params = `상품 수정 데이터를 만든다`(price, count, thumbnailImagePath, sizeUnit, sizeRate)
        return RestAssured.given().log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(ContentType.JSON)
            .body(params)
            .`when`().put(PRODUCT_ITEM_PATH.format(productId) + "/${productItemId}")
            .then().log().all()
            .extract()
    }

    fun `상품 아이템 삭제 요청을 한다`(
        productItemId: Long,
        productId: Long
    ): ExtractableResponse<Response> {
        return RestAssured.given().log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(ContentType.JSON)
            .`when`().delete(PRODUCT_ITEM_PATH.format(productId) + "/${productItemId}")
            .then().log().all()
            .extract()
    }

    /**
     * Create Request Data
     */
    private fun `상품 생성 데이터를 만든다`(
        productId: Long = PRODUCT_ITEM_PRODUCT_ID,
        price: Int = PRODUCT_ITEM_PRICE,
        count: Int = PRODUCT_ITEM_COUNT,
        thumbnailImagePath: String = PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH,
        sizeUnit: String = PRODUCT_ITEM_SIZE_UNIT,
        sizeRate: Int = PRODUCT_SIZE_RATE
    ): Map<String, String> {
        return mapOf(
            PRODUCT_ITEM_PRODUCT_ID_FIELD to productId.toString(),
            PRODUCT_ITEM_PRICE_FIELD to price.toString(),
            PRODUCT_ITEM_COUNT_FIELD to count.toString(),
            PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH_FIELD to thumbnailImagePath,
            PRODUCT_ITEM_SIZE_UNIT_FIELD to sizeUnit,
            PRODUCT_ITEM_SIZE_RATE_FIELD to sizeRate.toString()
        )
    }

    private fun `상품 수정 데이터를 만든다`(
        price: Int = PRODUCT_ITEM_PRICE,
        count: Int = PRODUCT_ITEM_COUNT,
        thumbnailImagePath: String = PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH,
        sizeUnit: String = PRODUCT_ITEM_SIZE_UNIT,
        sizeRate: Int = PRODUCT_SIZE_RATE
    ): Map<String, String> {
        return mapOf(
            PRODUCT_ITEM_PRICE_FIELD to price.toString(),
            PRODUCT_ITEM_COUNT_FIELD to count.toString(),
            PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH_FIELD to thumbnailImagePath,
            PRODUCT_ITEM_SIZE_UNIT_FIELD to sizeUnit,
            PRODUCT_ITEM_SIZE_RATE_FIELD to sizeRate.toString()
        )
    }

    fun `상품 아이템 생성 요청 됨`(
        productId: Long = PRODUCT_ITEM_PRODUCT_ID,
        price: Int = PRODUCT_ITEM_PRICE,
        count: Int = PRODUCT_ITEM_COUNT,
        thumbnailImagePath: String = PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH,
        sizeUnit: String = PRODUCT_ITEM_SIZE_UNIT,
        sizeRate: Int = PRODUCT_SIZE_RATE
    ): Long {
        val response = `상품 아이템 생성 요청을 한다`(productId, price, count, thumbnailImagePath, sizeUnit, sizeRate)
        val header = response.header("location")
        val removePrefix = header.removePrefix(PRODUCT_ITEM_PATH.format(productId)).removePrefix("/")
        return removePrefix.toLong()
    }

    /**
     * Response Assertion
     */
    fun `상품 아이템 생성됨`(response: ExtractableResponse<Response>) {
        AssertionsForInterfaceTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    fun `상품 아이템 수정됨`(response: ExtractableResponse<Response>) {
        AssertionsForInterfaceTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    fun `상품 아이템 삭제됨`(response: ExtractableResponse<Response>) {
        AssertionsForInterfaceTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
