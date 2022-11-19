package com.guacamole.api.production.domain.product

class Stock(
    var count: Int,
    var id: Long? = null
) {
    init {
        if(count < 0) {
            throw RuntimeException()
        }
    }

    fun decreaseByCount(count: Int = 1): Stock = Stock(Math.subtractExact(this.count, count))

}
