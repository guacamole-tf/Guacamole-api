package com.guacamole.api.product.domain

class Stock(
    var count: Int,
    var id: Long? = null
) {
    init {
        require(count >= DEFAULT_COUNT)
    }

    fun decreaseByCount(count: Int = DEFAULT_DECREASE_COUNT): Stock = Stock(Math.subtractExact(this.count, count))

    companion object {
        const val DEFAULT_COUNT: Int = 0
        const val DEFAULT_DECREASE_COUNT: Int = 1
    }
}
