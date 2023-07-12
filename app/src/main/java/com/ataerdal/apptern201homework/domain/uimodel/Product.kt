package com.ataerdal.apptern201homework.domain.uimodel

data class Product(
    val id: Int?,
    var newPrice: Int?, // ?
    val oldPrice: Double?,
    val image: String?,
    val name: String?,
    val quantity: Int?,
    val type: String?
)