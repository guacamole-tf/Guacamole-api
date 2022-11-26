package com.guacamole.api.acceptance

import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table
import javax.persistence.metamodel.EntityType


@Service
class DataBaseCleanUp : InitializingBean {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    private var tableNames: List<String>? = null

    override fun afterPropertiesSet() {
        tableNames = entityManager!!.metamodel.entities.stream()
            .filter { it.javaType.getAnnotation(Entity::class.java) != null }
            .map { tableNameByEntityClass(it) }
            .collect(Collectors.toList())
    }

    private fun tableNameByEntityClass(entityType: EntityType<*>): String {
        val annotation = entityType.javaType.getAnnotation(Table::class.java)
        if (annotation is Table) {
            return annotation.name
        }
        return entityType.name.uppercase().lowercase()
    }

    @Transactional
    fun execute() {
        entityManager!!.flush()
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate()
        for (tableName in tableNames!!) {
            entityManager.createNativeQuery("TRUNCATE TABLE $tableName").executeUpdate()
            entityManager.createNativeQuery("ALTER TABLE $tableName AUTO_INCREMENT = 1").executeUpdate()
        }
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate()
    }
}
