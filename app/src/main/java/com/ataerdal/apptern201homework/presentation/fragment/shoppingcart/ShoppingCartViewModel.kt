package com.ataerdal.apptern201homework.presentation.fragment.shoppingcart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ataerdal.apptern201homework.core.Response
import com.ataerdal.apptern201homework.domain.usecase.GetShoppingCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val getShoppingCartUseCase: GetShoppingCartUseCase
) : ViewModel() {

    private val _shoppingCartUiState = MutableStateFlow(ShoppingCartUiState())
    val shoppingCartUiState: StateFlow<ShoppingCartUiState> = _shoppingCartUiState.asStateFlow()

    fun getShoppingCart(cartId: Int) = viewModelScope.launch {
        getShoppingCartUseCase(cartId = cartId).collect { response ->
            when (response) {
                is Response.Loading -> _shoppingCartUiState.update { it.copy(loading = true) }
                is Response.Success -> _shoppingCartUiState.update { it.copy(cart = response.data) }
                is Response.Error -> _shoppingCartUiState.update { it.copy(error = response.message) }
            }
        }
    }
}