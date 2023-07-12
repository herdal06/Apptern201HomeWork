package com.ataerdal.apptern201homework.data.remote.dto.product

data class ProductDto(
    val currentStore: Any? = null,
    val id: Int?,
    val newPrice: Int?,
    val oldPrice: Double?,
    val productImage: String?,
    val productName: String?,
    val quantity: Int?,
    val type: String?
)