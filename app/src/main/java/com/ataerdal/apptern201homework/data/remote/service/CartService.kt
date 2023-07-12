package com.ataerdal.apptern201homework.data.remote.service

import com.ataerdal.apptern201homework.base.model.BaseResponse
import com.ataerdal.apptern201homework.data.remote.dto.cart.CartDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CartService {

    @GET("cart/{cartId}")
    suspend fun getShoppingCart(
        @Path("cartId") cartId: Int
    ): BaseResponse<CartDto>?
}