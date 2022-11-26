import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Component
class DataBaseCleanUp : InitializingBean {

    @PersistenceContext
    private val entityManager: EntityManager? = null
    private var tableNames: List<String>? = null

    override fun afterPropertiesSet() {
        tableNames = entityManager!!.metamodel.entities.stream()
            .filter { it.javaType.getAnnotation(Entity::class.java) != null }
            .map { it.name.lowercase().uppercase() }
            .collect(Collectors.toList())
    }

    @Transactional
    fun execute() {
        entityManager!!.flush()
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate()
        for (tableName in tableNames!!) {
            entityManager.createNativeQuery("TRUNCATE TABLE $tableName").executeUpdate()
            entityManager.createNativeQuery("ALTER TABLE $tableName ALTER COLUMN ID RESTART WITH 1").executeUpdate()
        }
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate()
    }
}
