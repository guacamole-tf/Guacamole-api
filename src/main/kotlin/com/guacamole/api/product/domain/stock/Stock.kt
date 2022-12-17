package com.guacamole.api.product.domain.stock

import com.guacamole.api.common.entity.BaseTimeEntity
import com.guacamole.api.common.extension.greaterOrEqual
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "stock")
@Entity
class Stock(
    @Column(name = "count")
    var count: Int,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) : BaseTimeEntity() {
    init {
        require(count.greaterOrEqual(DEFAULT_COUNT))
    }

    fun decreaseByCount(count: Int = DEFAULT_DECREASE_COUNT): Stock =
        Stock(Math.subtractExact(this.count, count), this.id)

    fun update(count: Int): Stock =
        Stock(count, this.id)

    companion object {
        private const val DEFAULT_COUNT: Int = 0
        private const val DEFAULT_DECREASE_COUNT: Int = 1
    }
}
