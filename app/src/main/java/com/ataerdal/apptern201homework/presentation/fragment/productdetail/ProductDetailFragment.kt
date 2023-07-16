package com.ataerdal.apptern201homework.presentation.fragment.productdetail

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ataerdal.apptern201homework.R
import com.ataerdal.apptern201homework.base.BaseFragment
import com.ataerdal.apptern201homework.databinding.DialogAddedToCartBinding
import com.ataerdal.apptern201homework.databinding.FragmentProductDetailBinding
import com.ataerdal.apptern201homework.domain.uimodel.Product
import com.ataerdal.apptern201homework.utils.extension.collectLatestLifecycleFlow
import com.ataerdal.apptern201homework.utils.extension.loadImage
import com.ataerdal.apptern201homework.utils.extension.prependDollarSign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {

    private val viewModel: ProductDetailViewModel by viewModels()

    private val args: ProductDetailFragmentArgs by navArgs()

    private fun getProductIdWithArgs() = args.productId
    override fun initialize() {
        collectProductDetail(getProductIdWithArgs())
        ibAddToCartClickListener()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentProductDetailBinding {
        return FragmentProductDetailBinding.inflate(inflater, container, false)
    }

    private fun collectProductDetail(productId: Int) {
        getProductDetail(productId)
        Log.d("ProductDetail", "collectProductDetail productId $productId")
        collectLatestLifecycleFlow(viewModel.productDetailUiState) { state ->
            state.product?.let { product ->
                Log.d("ProductDetail", "collectProductDetail product $product")
                prepareUI(product)
            }
        }
    }

    private fun prepareUI(product: Product?) = binding?.apply {
        product?.let {
            Log.d("ProductDetail", "product: $product")
            tvProductName.text = product.name
            tvProductNewPrice.text = product.newPrice.toString().prependDollarSign()
            ivProduct.loadImage(product.image)
        }
    }

    private fun getProductDetail(productId: Int) {
        viewModel.getProductDetail(productId)
        Log.d("ProductDetail", "productId $productId")
    }

    private fun ibAddToCartClickListener() = binding?.btnAddToCart?.setOnClickListener {
        addProductToCart(cartId = 5, productId = getProductIdWithArgs())
        val dialogView = layoutInflater.inflate(R.layout.dialog_added_to_cart, null)
        val alertDialogBuilder = AlertDialog.Builder(requireContext(), R.style.RoundedDialog)
        alertDialogBuilder.setView(dialogView)
        val alertDialog = alertDialogBuilder.create()

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.window?.setDimAmount(0.5f)

        alertDialog.show()

        val dialogBinding = DialogAddedToCartBinding.bind(dialogView)
        val btnGoToHome = dialogBinding.btnGoToHome

        btnGoToHome.setOnClickListener {
            alertDialog.dismiss()
            navigateToHomeScreen()
        }
    }

    private fun addProductToCart(cartId: Int, productId: Int) {
        viewModel.addProductToCart(cartId, productId)
    }

    private fun navigateToHomeScreen() {
        findNavController().navigate(R.id.action_productDetailFragment_to_homeFragment)
    }
}