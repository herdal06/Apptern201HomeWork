package com.ataerdal.apptern201homework.presentation.fragment.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ataerdal.apptern201homework.core.Response
import com.ataerdal.apptern201homework.domain.usecase.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {

    private val _productDetailUiState = MutableStateFlow(ProductDetailUiState())
    val productDetailUiState: StateFlow<ProductDetailUiState> = _productDetailUiState.asStateFlow()

    fun getProductDetail(productId: Int) = viewModelScope.launch {
        getProductDetailUseCase(productId).collect { response ->
            when (response) {
                is Response.Loading -> _productDetailUiState.update { it.copy(loading = true) }
                is Response.Success -> _productDetailUiState.update { it.copy(product = response.data) }
                is Response.Error -> _productDetailUiState.update { it.copy(error = response.message) }
            }
        }
    }
}