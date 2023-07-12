package com.ataerdal.apptern201homework.data.remote.dto.cart

import com.ataerdal.apptern201homework.data.remote.dto.product.ProductDto

data class CartDto(
    val id: Int?,
    val products: List<ProductDto>?,
    val token: Any? = null
)