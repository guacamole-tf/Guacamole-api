package com.guacamole.api.product.domain.category

import com.guacamole.api.common.entity.BaseTimeEntity
import com.guacamole.api.common.extension.greaterOrEqual
import javax.persistence.*

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

    fun update(name: String, parentId: Long) {
        this.name = name
        this.parentId = parentId
    }

    companion object {
        private const val DEFAULT_PARENT_ID: Long = 0L
    }
}
