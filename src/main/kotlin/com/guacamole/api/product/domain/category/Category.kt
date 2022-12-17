package com.guacamole.api.product.domain.category

import com.guacamole.api.common.entity.BaseTimeEntity
import com.guacamole.api.common.extension.greaterOrEqual
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "category")
@Entity
class Category(

    @Column(name = "name")
    var name: String,

    @Column(name = "parent_id")
    var parentId: Long = DEFAULT_PARENT_ID,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) : BaseTimeEntity() {
    init {
        require(name.isNotBlank())
        require(parentId.greaterOrEqual(DEFAULT_PARENT_ID))
    }

    fun update(name: String, parentId: Long): Category =
        Category(name, parentId, this.id)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    companion object {
        private const val DEFAULT_PARENT_ID: Long = 0L
    }
}
