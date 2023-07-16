package com.ataerdal.apptern201homework.domain.usecase

import com.ataerdal.apptern201homework.core.Response
import com.ataerdal.apptern201homework.data.repository.product.ProductRepository
import com.ataerdal.apptern201homework.domain.uimodel.Product
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(cartId: Int, productId: Int) = flow {
        try {
            emit(Response.Loading)
            val product = productRepository.addProductToCart(
                cartId = cartId,
                productId = productId,
            )
            emit(Response.Success(data = product))
        } catch (e: HttpException) {
            emit(Response.Error(message = e.message))
        } catch (e: IOException) {
            emit(Response.Error(message = e.message))
        }
    }
}