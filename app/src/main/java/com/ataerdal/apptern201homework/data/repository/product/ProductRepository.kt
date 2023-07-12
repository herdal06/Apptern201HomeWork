package com.ataerdal.apptern201homework.data.repository.product

import com.ataerdal.apptern201homework.domain.uimodel.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>?
}