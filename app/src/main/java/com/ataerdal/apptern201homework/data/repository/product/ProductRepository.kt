package com.ataerdal.apptern201homework.data.repository.product

import com.ataerdal.apptern201homework.domain.uimodel.Cart
import com.ataerdal.apptern201homework.domain.uimodel.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>?
    suspend fun getProductDetail(productId: Int): Product?
    suspend fun addProductToCart(cartId: Int, productId: Int)
    suspend fun removeProductFromCart(cartId: Int, productId: Int): Cart?
}