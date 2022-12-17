package com.guacamole.api.acceptance.test

import com.guacamole.api.acceptance.util.MySQLTestContainer
import io.restassured.RestAssured
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.jdbc.datasource.init.DataSourceInitializer

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcceptanceTest : MySQLTestContainer() {

    @LocalServerPort
    var port = 0

    @Autowired
    private lateinit var dataSourceInitializer: DataSourceInitializer

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
        dataSourceInitializer.afterPropertiesSet()
    }

    @AfterEach
    internal fun tearDown() {
        dataSourceInitializer.destroy()
    }
}
