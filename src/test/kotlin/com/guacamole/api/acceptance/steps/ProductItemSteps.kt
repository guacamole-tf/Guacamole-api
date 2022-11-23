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
    - 상품 아이템 등록 시 상품 아이템의 가격을 입력해야 한다.
    - 상품 아이템 등록 시 상품 아이템의 재고 수량을 입력해야 한다.
    - 상품 아이템 등록 시 상품 아이템의 썸네일 이미지를 입력할 수 있다.
    - 상품 아이템 등록 시 **[상품 판매 정책](https://www.notion.so/58c4b7f0b599479cbcb2a136fb5ea6fc)**을 등록할 수 있다.
    - 상품 아이템 등록 시 상품 아이템의 개별 규격(숫자와 단위)을 입력할 수 있다.
     */
    fun `상품 아이템 생성 요청을 한다`(
        productId: Long = PRODUCT_ITEM_PRODUCT_ID,
        price: Int = PRODUCT_ITEM_PRICE,
        count: Int = PRODUCT_ITEM_COUNT,
        thumbnailImagePath: String = PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH,
        sizeUnit: Long = PRODUCT_ITEM_SIZE_UNIT,
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

    /**
     * Create Request Data
     */
    private fun `상품 생성 데이터를 만든다`(
        productId: Long = PRODUCT_ITEM_PRODUCT_ID,
        price: Int = PRODUCT_ITEM_PRICE,
        count: Int = PRODUCT_ITEM_COUNT,
        thumbnailImagePath: String = PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH,
        sizeUnit: Long = PRODUCT_ITEM_SIZE_UNIT,
        sizeRate: Int = PRODUCT_SIZE_RATE
    ): Map<String, String> {
        return mapOf(
            PRODUCT_ITEM_PRODUCT_ID_FIELD to productId.toString(),
            PRODUCT_ITEM_PRICE_FIELD to price.toString(),
            PRODUCT_ITEM_COUNT_FIELD to count.toString(),
            PRODUCT_ITEM_THUMBNAIL_IMAGE_PATH_FIELD to thumbnailImagePath,
            PRODUCT_ITEM_SIZE_UNIT_FIELD to sizeUnit.toString(),
            PRODUCT_ITEM_SIZE_RATE_FIELD to sizeRate.toString()
        )
    }

    /**
     * Response Assertion
     */
    fun `상품 아이템 생성됨`(response: ExtractableResponse<Response>) {
        AssertionsForInterfaceTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
