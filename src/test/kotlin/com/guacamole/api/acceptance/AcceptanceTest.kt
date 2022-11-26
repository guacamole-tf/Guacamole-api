package com.guacamole.api.acceptance

import io.restassured.RestAssured
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Profile

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcceptanceTest {

    @LocalServerPort
    var port = 0

    @Autowired
    private lateinit var dataBaseCleanUp: DataBaseCleanUp

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
        dataBaseCleanUp.execute()
    }
}
