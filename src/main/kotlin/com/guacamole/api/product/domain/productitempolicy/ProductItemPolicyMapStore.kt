package com.guacamole.api.product.domain.productitempolicy

import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger

@Service
class ProductItemPolicyMapStore(
    private var store: MutableMap<Long, ProductItemPolicy> = HashMap(),
    private var count: AtomicInteger = AtomicInteger()
) : ProductItemPolicyStore {
    override fun saveAndFlush(productItemPolicy: ProductItemPolicy): ProductItemPolicy {
        if (store.containsKey(productItemPolicy.id)) {
            store[productItemPolicy.id!!] = productItemPolicy
            return ProductItemPolicy(
                productItemPolicy.productItemId,
                productItemPolicy.limitUnit,
                productItemPolicy.limitCount,
                productItemPolicy.bannedAt,
                productItemPolicy.discountUnit,
                productItemPolicy.discountRate,
                count.get().toLong()
            )
        }

        store[count.incrementAndGet().toLong()] = productItemPolicy
        return ProductItemPolicy(
            productItemPolicy.productItemId,
            productItemPolicy.limitUnit,
            productItemPolicy.limitCount,
            productItemPolicy.bannedAt,
            productItemPolicy.discountUnit,
            productItemPolicy.discountRate,
            count.get().toLong()
        )
    }

    override fun findById(productItemPolicyId: Long): ProductItemPolicy {
        if (store.containsKey(productItemPolicyId)) {
            return store[productItemPolicyId]!!
        }
        throw RuntimeException()
    }

    override fun remove(productItemPolicyId: Long) {
        if (store.containsKey(productItemPolicyId)) {
            store.remove(productItemPolicyId)
            return
        }
        throw RuntimeException()
    }

    override fun existsById(productItemPolicyId: Long): Boolean =
        store.containsKey(productItemPolicyId)
}
