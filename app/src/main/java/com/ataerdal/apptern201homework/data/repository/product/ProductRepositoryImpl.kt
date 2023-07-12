package com.ataerdal.apptern201homework.data.repository.product

import com.ataerdal.apptern201homework.data.remote.service.ProductService
import com.ataerdal.apptern201homework.domain.mapper.ProductDtoMapper
import com.ataerdal.apptern201homework.domain.uimodel.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productDtoMapper: ProductDtoMapper
) : ProductRepository {
    override suspend fun getAllProducts(): List<Product>? {
        return productService.getAllProducts()?.result?.let { products ->
            productDtoMapper.toDomainList(products)
        }
    }
}