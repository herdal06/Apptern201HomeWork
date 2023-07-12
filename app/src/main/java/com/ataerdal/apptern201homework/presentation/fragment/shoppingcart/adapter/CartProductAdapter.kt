package com.ataerdal.apptern201homework.presentation.fragment.shoppingcart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ataerdal.apptern201homework.databinding.ItemCartProductBinding
import com.ataerdal.apptern201homework.domain.uimodel.Product

class CartProductAdapter(
    private val onClickProduct: ((productId: Int) -> Unit)?
) : ListAdapter<Product, CartProductViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder =
        CartProductViewHolder(
            ItemCartProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickProduct
        )

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem == newItem
        }
    }
}