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
}