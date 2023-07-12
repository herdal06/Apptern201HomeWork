package com.ataerdal.apptern201homework.data.remote.service

import com.ataerdal.apptern201homework.base.model.BaseResponse
import com.ataerdal.apptern201homework.data.remote.dto.product.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products")
    suspend fun getAllProducts(): BaseResponse<List<ProductDto>>?

    @GET("products/{id}")
    suspend fun getProductDetail(
        @Path("id") productId: Int
    ): BaseResponse<ProductDto>?
}