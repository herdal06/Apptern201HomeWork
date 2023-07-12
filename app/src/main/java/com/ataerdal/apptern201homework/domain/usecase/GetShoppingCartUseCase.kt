package com.ataerdal.apptern201homework.domain.usecase

import com.ataerdal.apptern201homework.core.Response
import com.ataerdal.apptern201homework.data.repository.cart.CartRepository
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetShoppingCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke(cartId: Int) = flow {
        try {
            emit(Response.Loading)
            val cart = cartRepository.getShoppingCart(cartId = cartId)
            emit(Response.Success(data = cart))
        } catch (e: HttpException) {
            emit(Response.Error(message = e.message))
        } catch (e: IOException) {
            emit(Response.Error(message = e.message))
        }
    }
}