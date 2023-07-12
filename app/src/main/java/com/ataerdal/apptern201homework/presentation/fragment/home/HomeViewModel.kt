package com.ataerdal.apptern201homework.presentation.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ataerdal.apptern201homework.core.Response
import com.ataerdal.apptern201homework.domain.usecase.GetAlProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAlProductsUseCase: GetAlProductsUseCase
) : ViewModel() {

    private val _productsUiState = MutableStateFlow(ProductsUiState())
    val productsUiState: StateFlow<ProductsUiState> = _productsUiState.asStateFlow()

     fun getAllProducts() = viewModelScope.launch {
        getAlProductsUseCase().collect { response ->
            when (response) {
                is Response.Loading -> _productsUiState.update { it.copy(loading = true) }
                is Response.Success -> _productsUiState.update { it.copy(doctors = response.data) }
                is Response.Error -> _productsUiState.update { it.copy(error = response.message) }
            }
        }
    }
}