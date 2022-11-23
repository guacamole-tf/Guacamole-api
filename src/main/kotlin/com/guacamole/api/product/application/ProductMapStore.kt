package com.guacamole.api.product.application

import com.guacamole.api.product.domain.Product
import com.guacamole.api.product.domain.store.ProductStore
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger

@Service
class ProductMapStore(
    private var store: MutableMap<Long, Product> = HashMap(),
    private var count: AtomicInteger = AtomicInteger()
) : ProductStore {
    override fun save(product: Product): Product {
        if (store.containsKey(product.id)) {
            store[product.id!!] = product
            return Product(
                product.categoryId,
                product.name,
                product.descriptionImagePath,
                product.originPlace,
                product.detailDescription,
                1L
            )
        }

        store[count.incrementAndGet().toLong()] = product
        return Product(
            product.categoryId,
            product.name,
            product.descriptionImagePath,
            product.originPlace,
            product.detailDescription,
            count.get().toLong()
        )
    }

    override fun findById(productId: Long): Product {
        if (store.containsKey(productId)) {
            return store[productId]!!
        }
        throw RuntimeException()
    }

    override fun remove(productId: Long) {
        if (store.containsKey(productId)) {
            store.remove(productId)
            return
        }
        throw RuntimeException()
    }
}
