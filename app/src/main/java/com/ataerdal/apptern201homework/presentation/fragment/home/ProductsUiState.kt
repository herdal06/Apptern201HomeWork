package com.ataerdal.apptern201homework.presentation.fragment.home

import com.ataerdal.apptern201homework.domain.uimodel.Product

data class ProductsUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val doctors: List<Product>? = emptyList(),
)