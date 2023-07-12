package com.ataerdal.apptern201homework.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ataerdal.apptern201homework.base.BaseFragment
import com.ataerdal.apptern201homework.databinding.FragmentHomeBinding
import com.ataerdal.apptern201homework.presentation.fragment.home.adapter.ProductAdapter
import com.ataerdal.apptern201homework.utils.extension.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter(::onClickProduct)
    }

    override fun initialize() {
        setupProductAdapter()
        collectUiState()
    }

    private fun setupProductAdapter() {
        binding?.rvProducts?.adapter = productAdapter
    }

    private fun collectUiState() {
        getAllProducts()
        collectLatestLifecycleFlow(viewModel.productsUiState) { state ->
            state.products.let { products ->
                productAdapter.submitList(products)
            }
        }
    }

    private fun getAllProducts() {
        viewModel.getAllProducts()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    private fun onClickProduct(productId: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
                productId
            )
        )
    }
}