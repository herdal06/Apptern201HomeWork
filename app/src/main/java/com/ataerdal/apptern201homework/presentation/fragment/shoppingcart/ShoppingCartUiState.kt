package com.ataerdal.apptern201homework.presentation.fragment.shoppingcart

import com.ataerdal.apptern201homework.domain.uimodel.Cart

data class ShoppingCartUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val cart: Cart? = null,
)