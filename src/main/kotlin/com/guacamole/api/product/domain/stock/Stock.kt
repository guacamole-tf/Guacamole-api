package com.guacamole.api.product.domain.stock

import com.guacamole.api.common.entity.BaseTimeEntity
import javax.persistence.*

@Table(name = "stock")
@Entity
class Stock(
    @Column(name = "count")
    var count: Int,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) : BaseTimeEntity() {
    init {
        require(count >= DEFAULT_COUNT)
    }

    fun decreaseByCount(count: Int = DEFAULT_DECREASE_COUNT): Stock = Stock(Math.subtractExact(this.count, count))

    companion object {
        const val DEFAULT_COUNT: Int = 0
        const val DEFAULT_DECREASE_COUNT: Int = 1
    }
}
