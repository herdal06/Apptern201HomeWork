package com.ataerdal.apptern201homework.domain.mapper

import com.ataerdal.apptern201homework.base.Mapper
import com.ataerdal.apptern201homework.data.remote.dto.cart.CartDto
import com.ataerdal.apptern201homework.domain.uimodel.Cart
import javax.inject.Inject

class CartDtoMapper @Inject constructor(
    private val productDtoMapper: ProductDtoMapper
) : Mapper<CartDto, Cart> {
    override fun toDomain(t: CartDto): Cart {
        val products = t.products?.let { productDtoMapper.toDomainList(it) }
        return Cart(
            id = t.id,
            products = products
        )
    }

    override fun fromDomain(domain: Cart): CartDto {
        val productDtos = domain.products?.let { productDtoMapper.fromDomainList(it) }
        return CartDto(
            id = domain.id,
            products = productDtos
        )
    }
}