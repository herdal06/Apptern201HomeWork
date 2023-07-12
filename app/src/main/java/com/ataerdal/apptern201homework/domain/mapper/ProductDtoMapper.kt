package com.ataerdal.apptern201homework.domain.mapper

import com.ataerdal.apptern201homework.base.Mapper
import com.ataerdal.apptern201homework.data.remote.dto.product.ProductDto
import com.ataerdal.apptern201homework.domain.uimodel.Product

class ProductDtoMapper : Mapper<ProductDto, Product> {
    override fun toDomain(t: ProductDto): Product = Product(
        id = t.id,
        newPrice = t.newPrice,
        oldPrice = t.oldPrice,
        image = t.productImage,
        name = t.productName,
        quantity = t.quantity,
        type = t.type
    )

    override fun fromDomain(domain: Product): ProductDto = ProductDto(
        id = domain.id,
        newPrice = domain.newPrice,
        oldPrice = domain.oldPrice,
        productImage = domain.image,
        productName = domain.name,
        quantity = domain.quantity,
        type = domain.type
    )

    fun toDomainList(tList: List<ProductDto>): List<Product> {
        return tList.map { toDomain(it) }
    }
}