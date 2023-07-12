package com.ataerdal.apptern201homework.presentation.fragment.shoppingcart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ataerdal.apptern201homework.base.BaseFragment
import com.ataerdal.apptern201homework.databinding.FragmentShoppingCartBinding
import com.ataerdal.apptern201homework.presentation.fragment.shoppingcart.adapter.CartProductAdapter
import com.ataerdal.apptern201homework.utils.extension.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartFragment : BaseFragment<FragmentShoppingCartBinding>() {

    private val viewModel: ShoppingCartViewModel by viewModels()

    private val cartProductAdapter: CartProductAdapter by lazy {
        CartProductAdapter(::onClickProduct)
    }

    override fun initialize() {
        setupCartProductAdapter()
        collectUiState()
    }

    private fun setupCartProductAdapter() {
        binding?.rvCartProducts?.adapter = cartProductAdapter
    }

    private fun collectUiState() {
        getShoppingCart(2) // ?
        collectLatestLifecycleFlow(viewModel.shoppingCartUiState) { state ->
            state.cart.let { cart ->
                cartProductAdapter.submitList(cart?.products)
            }
        }
    }

    private fun getShoppingCart(cartId: Int) {
        viewModel.getShoppingCart(cartId)
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentShoppingCartBinding {
        return FragmentShoppingCartBinding.inflate(inflater, container, false)
    }

    private fun onClickProduct(productId: Int) {
        findNavController().navigate(
            ShoppingCartFragmentDirections.actionShoppingCartFragmentToProductDetailFragment(
                productId
            )
        )
    }
}