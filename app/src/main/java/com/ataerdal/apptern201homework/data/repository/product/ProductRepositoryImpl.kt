package com.ataerdal.apptern201homework.data.repository.product

import com.ataerdal.apptern201homework.data.remote.service.ProductService
import com.ataerdal.apptern201homework.domain.mapper.CartDtoMapper
import com.ataerdal.apptern201homework.domain.mapper.ProductDtoMapper
import com.ataerdal.apptern201homework.domain.uimodel.Cart
import com.ataerdal.apptern201homework.domain.uimodel.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productDtoMapper: ProductDtoMapper,
    private val cartDtoMapper: CartDtoMapper
) : ProductRepository {
    override suspend fun getAllProducts(): List<Product>? {
        return productService.getAllProducts()?.result?.let { products ->
            productDtoMapper.toDomainList(products)
        }
    }

    override suspend fun getProductDetail(productId: Int): Product? {
        return productService.getProductDetail(productId = productId)?.result
            ?.let { productDtoMapper.toDomain(it) }
    }

    override suspend fun addProductToCart(cartId: Int, productId: Int) {
        productService.addProductToCart(
            cartId = cartId,
            productId = productId,
        )
    }

    override suspend fun removeProductFromCart(cartId: Int, productId: Int): Cart? {
        return productService.removeProductFromCart(
            cartId = cartId,
            productId = productId
        )?.result?.let { cartDto ->
            cartDtoMapper.toDomain(cartDto)
        }
    }
}