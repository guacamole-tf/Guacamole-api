package com.guacamole.api.acceptance.util

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer

abstract class MySQLTestContainer {
    companion object {
        private const val DOCKER_MYSQL_IMAGE = "mysql:latest"
        private const val MYSQL_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver"
        private const val DATABASE_NAME: String = "guacamole_product"
        private const val URL: String =
            "jdbc:mysql://localhost:%s/$DATABASE_NAME?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8&serverTimezone=UTC"
        private const val USERNAME: String = "root"
        private const val PASSWORD: String = "password"

        @JvmStatic
        private val MY_SQL_CONTAINER = MySQLContainer<Nothing>(DOCKER_MYSQL_IMAGE)
            .apply { withDatabaseName(DATABASE_NAME) }
            .apply { withUsername(USERNAME) }
            .apply { withPassword(PASSWORD) }
            .apply { start() }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.driver-class-name") { MYSQL_DRIVER_CLASS_NAME }
            registry.add("spring.datasource.url") { URL.format(MY_SQL_CONTAINER.firstMappedPort) }
            registry.add("spring.datasource.username") { USERNAME }
            registry.add("spring.datasource.password") { PASSWORD }
        }
    }
}
