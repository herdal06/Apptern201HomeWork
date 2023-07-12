package com.ataerdal.apptern201homework.presentation.fragment.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ataerdal.apptern201homework.base.BaseFragment
import com.ataerdal.apptern201homework.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {
    override fun initialize() {

    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentProductDetailBinding {
        return FragmentProductDetailBinding.inflate(inflater, container, false)
    }
}