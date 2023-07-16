package com.ataerdal.apptern201homework.presentation.fragment.shoppingcart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ataerdal.apptern201homework.base.BaseFragment
import com.ataerdal.apptern201homework.databinding.FragmentShoppingCartBinding
import com.ataerdal.apptern201homework.domain.uimodel.Product
import com.ataerdal.apptern201homework.presentation.fragment.shoppingcart.adapter.CartProductAdapter
import com.ataerdal.apptern201homework.utils.extension.collectLatestLifecycleFlow
import com.ataerdal.apptern201homework.utils.extension.prependDollarSign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartFragment : BaseFragment<FragmentShoppingCartBinding>() {

    private val viewModel: ShoppingCartViewModel by viewModels()

    private val cartProductAdapter: CartProductAdapter by lazy {
        CartProductAdapter(::onClickProduct, ::onClickDeleteIcon)
    }

    override fun initialize() {
        setupCartProductAdapter()
        collectUiState()
        btnBuyClickListener()
    }

    private fun setupCartProductAdapter() {
        binding?.rvCartProducts?.adapter = cartProductAdapter
    }

    private fun collectUiState() {
        getShoppingCart(CART_ID) // ?
        collectLatestLifecycleFlow(viewModel.shoppingCartUiState) { state ->
            state.cart.let { cart ->
                cartProductAdapter.submitList(cart?.products)
                binding?.tvBasketAmount?.text =
                    calculateTotalPrice(cart?.products).toString().prependDollarSign() // ?
            }
        }
    }

    private fun getShoppingCart(cartId: Int) {
        viewModel.getShoppingCart(cartId)
    }

    private fun clearCart(cartId: Int) {
        viewModel.clearCart(cartId)
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

    private fun onClickDeleteIcon(productId: Int) {
        viewModel.removeProductFromCart(cartId = 5, productId = productId)
    }

    private fun btnBuyClickListener() = binding?.btnBuy?.setOnClickListener {
        clearCart(CART_ID)
    }

    // ?
    private fun calculateTotalPrice(products: List<Product>?): Int {
        var totalPrice = 0
        products?.forEach { product ->
            totalPrice += product.newPrice ?: 0
        }
        return totalPrice
    }

    companion object {
        const val CART_ID = 5
    }
}