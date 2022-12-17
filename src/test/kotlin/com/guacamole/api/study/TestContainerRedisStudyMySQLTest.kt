package com.guacamole.api.study

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
class TestContainerRedisStudyMySQLTest {
    private var underTest: RedisBackedCache? = null

    @Container
    var redis = GenericContainer(DockerImageName.parse("redis:5.0.3-alpine"))
        .withExposedPorts(6379)

    @BeforeEach
    fun setUp() {
        val address: String = redis.getHost()
        val port: Int = redis.getFirstMappedPort()

        underTest = RedisBackedCache(address, port)
    }

    @Disabled
    @Test
    fun testSimplePutAndGet() {
        underTest!!.put("test", "example")
        val retrieved: String = underTest!!.get("test")

        assertThat(retrieved).isEqualTo("example")
    }

    @Disabled
    @Test
    fun testSimplePutAndGet2() {
        underTest!!.put("test", "example")
        val retrieved: String = underTest!!.get("test")

        assertThat(retrieved).isEqualTo("example")
    }

    class RedisBackedCache(hostname: String, port: Int) {
        private val connection: StatefulRedisConnection<String, String>

        init {
            val client = RedisClient.create("redis://$hostname:$port/0")
            connection = client.connect()
        }

        operator fun get(key: String): String {
            return connection.sync().get(key)
        }

        fun put(key: String, value: String) {
            connection.sync().set(key, value)
        }
    }
}
