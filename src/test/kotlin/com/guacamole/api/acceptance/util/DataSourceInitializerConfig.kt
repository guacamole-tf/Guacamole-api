package com.guacamole.api.acceptance.util

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import javax.sql.DataSource

@Configuration
class DataSourceInitializerConfig {

    @Bean
    fun dataSourceInitializer(dataSource: DataSource): DataSourceInitializer {
        val initializer = DataSourceInitializer()
        initializer.setDataSource(dataSource)
        initializer.setDatabasePopulator(resourceDatabasePopulator())
        initializer.setDatabaseCleaner(resourceDatabaseCleaner())
        return initializer
    }

    private fun resourceDatabasePopulator(
        classPathResource: String = schemaSQLPath
    ): ResourceDatabasePopulator {
        val databasePopulator = ResourceDatabasePopulator()
        databasePopulator.addScript(ClassPathResource(classPathResource))
        return databasePopulator
    }

    private fun resourceDatabaseCleaner(
        classPathResource: String = truncateSQLPath,
        ignoreFailedDrops: Boolean = true
    ): ResourceDatabasePopulator {
        val databaseCleaner = ResourceDatabasePopulator()
        databaseCleaner.addScript(ClassPathResource(classPathResource))
        databaseCleaner.setIgnoreFailedDrops(ignoreFailedDrops)
        return databaseCleaner
    }

    companion object {
        private const val schemaSQLPath = "db/sql/schema.sql"
        private const val truncateSQLPath = "db/sql/truncate.sql"
    }
}
