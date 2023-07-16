package com.ataerdal.apptern201homework.domain.usecase

import com.ataerdal.apptern201homework.core.Response
import com.ataerdal.apptern201homework.data.repository.product.ProductRepository
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class RemoveProductFromCartUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(cartId: Int, productId: Int) = flow {
        try {
            emit(Response.Loading)
            val products = productRepository.removeProductFromCart(cartId, productId)
            emit(Response.Success(data = products))
        } catch (e: HttpException) {
            emit(Response.Error(message = e.message))
        } catch (e: IOException) {
            emit(Response.Error(message = e.message))
        }
    }
}