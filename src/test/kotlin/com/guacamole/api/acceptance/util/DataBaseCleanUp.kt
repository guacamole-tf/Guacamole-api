package com.guacamole.api.acceptance.util

import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table
import javax.persistence.metamodel.EntityType

@Deprecated("EntityManager 기반 DB Truncate")
@Component
class DataBaseCleanUp : InitializingBean {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    private var tableNames: List<String>? = null

    override fun afterPropertiesSet() {
        tableNames = entityManager!!.metamodel.entities.stream()
            .filter { it.javaType.getAnnotation(Entity::class.java) != null }
            .map { dataBaseTableName(it) }
            .collect(Collectors.toList())
    }

    private fun dataBaseTableName(entityType: EntityType<*>): String {
        val annotation = entityType.javaType.getAnnotation(Table::class.java)
        if (annotation is Table) {
            return tableNameByAnnotationOrEntityType(annotation, entityType)
        }
        return entityType.name.uppercase().lowercase()
    }

    private fun tableNameByAnnotationOrEntityType(
        annotation: Table,
        entityType: EntityType<*>
    ): String {
        if (annotation.name.isBlank()) {
            return entityType.name.lowercase()
        }
        return annotation.name
    }

    @Transactional
    fun execute() {
        entityManager!!.flush()
        entityManager.createNativeQuery(SET_FOREIGN_KEY_CHECKS.format(DATABASE_FALSE)).executeUpdate()
        for (tableName in tableNames!!) {
            entityManager.createNativeQuery(TRUNCATE_TABLE.format(tableName)).executeUpdate()
            entityManager.createNativeQuery(RESET_AUTO_INCREMENT.format(tableName)).executeUpdate()
        }
        entityManager.createNativeQuery(SET_FOREIGN_KEY_CHECKS.format(DATABASE_TRUE)).executeUpdate()
    }

    companion object {
        private const val DATABASE_FALSE: Long = 0
        private const val DATABASE_TRUE: Long = 1

        private const val SET_FOREIGN_KEY_CHECKS: String = "SET FOREIGN_KEY_CHECKS = %s"
        private const val TRUNCATE_TABLE: String = "TRUNCATE TABLE %s"
        private const val RESET_AUTO_INCREMENT: String = "ALTER TABLE %s AUTO_INCREMENT = 1"
    }
}
