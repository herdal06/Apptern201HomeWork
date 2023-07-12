package com.ataerdal.apptern201homework.data.repository.cart

import com.ataerdal.apptern201homework.domain.uimodel.Cart

interface CartRepository {
    suspend fun getShoppingCart(cartId: Int): Cart?
}