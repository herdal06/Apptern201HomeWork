package com.ataerdal.apptern201homework.presentation.fragment.productdetail

import com.ataerdal.apptern201homework.domain.uimodel.Product

data class ProductDetailUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val product: Product? = null,
)