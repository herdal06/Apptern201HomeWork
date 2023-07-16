package com.ataerdal.apptern201homework.presentation.fragment.shoppingcart

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ataerdal.apptern201homework.R
import com.ataerdal.apptern201homework.base.BaseFragment
import com.ataerdal.apptern201homework.databinding.FragmentShoppingCartBinding
import com.ataerdal.apptern201homework.domain.uimodel.Product
import com.ataerdal.apptern201homework.presentation.fragment.shoppingcart.adapter.CartProductAdapter
import com.ataerdal.apptern201homework.utils.extension.collectLatestLifecycleFlow
import com.ataerdal.apptern201homework.utils.extension.prependDollarSign
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        getShoppingCart(CART_ID)
        collectLatestLifecycleFlow(viewModel.shoppingCartUiState) { state ->
            state.cart.let { cart ->
                val hasProducts = !cart?.products.isNullOrEmpty()

                binding?.rvCartProducts?.isVisible = false
                binding?.tvNoProduct?.isVisible = false

                cartProductAdapter.submitList(cart?.products)
                binding?.tvBasketAmount?.text =
                    calculateTotalPrice(cart?.products).toString().prependDollarSign()

                binding?.btnBuy?.isEnabled = hasProducts

                binding?.rvCartProducts?.isVisible = hasProducts
                binding?.tvNoProduct?.isVisible = !hasProducts
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
        viewModel.removeProductFromCart(cartId = CART_ID, productId = productId)
    }

    private fun btnBuyClickListener() {
        binding?.btnBuy?.setOnClickListener {
            val cart = viewModel.shoppingCartUiState.value.cart
            if (cart?.products.isNullOrEmpty()) {
                return@setOnClickListener
            }

            clearCart(CART_ID)

            showCustomDialog()
        }

        val enableButton = !viewModel.shoppingCartUiState.value.cart?.products.isNullOrEmpty()
        binding?.btnBuy?.isEnabled = enableButton
    }

    // ?
    private fun calculateTotalPrice(products: List<Product>?): Int {
        var totalPrice = 0
        products?.forEach { product ->
            totalPrice += product.newPrice ?: 0
        }
        return totalPrice
    }

    private fun showCustomDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_cart_clear, null)
        val alertDialogBuilder = AlertDialog.Builder(requireContext(), R.style.RoundedDialog)
        alertDialogBuilder.setView(dialogView)
        val alertDialog = alertDialogBuilder.create()

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.window?.setDimAmount(0.5f)

        alertDialog.show()

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            alertDialog.dismiss()
        }
    }

    // Error: ConstraintLayout{1f7df6f V.E...... ........ 0,0-1080,1813} does not have a NavController set
   /* private fun navigateToHomeScreen() {
        findNavController().navigate(ShoppingCartFragmentDirections.actionShoppingCartFragmentToHomeFragment())
    }
    */

    companion object {
        const val CART_ID = 5
    }
}