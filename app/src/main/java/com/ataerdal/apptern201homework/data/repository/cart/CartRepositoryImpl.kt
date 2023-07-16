package com.ataerdal.apptern201homework.data.repository.cart

import com.ataerdal.apptern201homework.data.remote.service.CartService
import com.ataerdal.apptern201homework.domain.mapper.CartDtoMapper
import com.ataerdal.apptern201homework.domain.uimodel.Cart
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartService: CartService,
    private val cartDtoMapper: CartDtoMapper
) : CartRepository {
    override suspend fun getShoppingCart(cartId: Int): Cart? {
        return cartService.getShoppingCart(cartId = cartId)?.result?.let { cartDto ->
            cartDtoMapper.toDomain(cartDto)
        }
    }

    override suspend fun clearCart(cartId: Int): Cart? {
        return cartService.clearCart(cartId = cartId)?.result?.let { cartDto ->
            cartDtoMapper.toDomain(cartDto)
        }
    }

    override suspend fun removeProductFromCart(cartId: Int, productId: Int): Cart? {
        return cartService.removeProductFromCart(
            cartId = cartId,
            productId = productId
        )?.result?.let { cartDto ->
            cartDtoMapper.toDomain(cartDto)
        }
    }
}