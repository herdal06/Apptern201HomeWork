package com.ataerdal.apptern201homework.data.remote.service

import com.ataerdal.apptern201homework.base.model.BaseResponse
import com.ataerdal.apptern201homework.data.remote.dto.cart.CartDto
import com.ataerdal.apptern201homework.data.remote.dto.product.ProductDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductService {

    @GET("products")
    suspend fun getAllProducts(): BaseResponse<List<ProductDto>>?

    @GET("products/{id}")
    suspend fun getProductDetail(
        @Path("id") productId: Int
    ): BaseResponse<ProductDto>?

    @POST("addtocart/{cartId}/{productId}")
    suspend fun addProductToCart(
        @Path("cartId") cartId: Int,
        @Path("productId") productId: Int
    )

    @GET("removeproduct/{cartId}/{productId}")
    suspend fun removeProductFromCart(
        @Path("cartId") cartId: Int,
        @Path("productId") productId: Int
    ): BaseResponse<CartDto>?
}