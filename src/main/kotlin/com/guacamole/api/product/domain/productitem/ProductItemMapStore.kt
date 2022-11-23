package com.guacamole.api.product.domain.productitem

import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger

@Service
class ProductItemMapStore(
    private var store: MutableMap<Long, ProductItem> = HashMap(),
    private var count: AtomicInteger = AtomicInteger()
) : ProductItemStore {
    override fun saveAndFlush(productItem: ProductItem): ProductItem {
        if (store.containsKey(productItem.id)) {
            store[productItem.id!!] = productItem
            return ProductItem(
                productItem.productId,
                productItem.stockId,
                productItem.sizeUnit,
                productItem.sizeRate,
                productItem.price,
                count.get().toLong()
            )
        }

        store[count.incrementAndGet().toLong()] = productItem
        return ProductItem(
            productItem.productId,
            productItem.stockId,
            productItem.sizeUnit,
            productItem.sizeRate,
            productItem.price,
            count.get().toLong()
        )
    }

    override fun findById(productItemId: Long): ProductItem {
        if (store.containsKey(productItemId)) {
            return store[productItemId]!!
        }
        throw RuntimeException()
    }

    override fun remove(productItemId: Long) {
        if (store.containsKey(productItemId)) {
            store.remove(productItemId)
            return
        }
        throw RuntimeException()
    }

    override fun existById(productItemId: Long): Boolean =
        store.containsKey(productItemId)
}
