package com.guacamole.api.study

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.sql.DriverManager

@Testcontainers
object TestContainerMySQLStudyMySQLTest {

    @Container
    private val mysqlContainer = MySQLContainer<Nothing>("mysql:latest").apply {
        withDatabaseName("guacamole_product")
        withUsername("root")
        withPassword("password")
    }

    @JvmStatic
    @DynamicPropertySource
    fun properties(registry: DynamicPropertyRegistry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
        registry.add("spring.datasource.password", mysqlContainer::getPassword)
        registry.add("spring.datasource.username", mysqlContainer::getUsername)
    }

    @Disabled
    @Test
    fun test() {
        val expected = "happy"

        val connection =
            DriverManager.getConnection(mysqlContainer.jdbcUrl, mysqlContainer.username, mysqlContainer.password)
        val statement = connection.createStatement()
        statement.execute("CREATE TABLE $expected(id bigint primary key);")
        val resultSet = statement.executeQuery("SHOW TABLES")
        resultSet.next()

        val actual = resultSet.getString(1)
        assertThat(actual).isEqualTo(expected)
    }
}
