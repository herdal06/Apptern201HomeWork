package com.ataerdal.apptern201homework.presentation.fragment.shoppingcart.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ataerdal.apptern201homework.databinding.ItemCartProductBinding
import com.ataerdal.apptern201homework.domain.uimodel.Product
import com.ataerdal.apptern201homework.utils.extension.loadImage

class CartProductViewHolder(
    private val binding: ItemCartProductBinding,
    private val onClickProduct: ((productId: Int) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) = binding.apply {

        tvProductName.text = product.name
        ivProduct.loadImage(product.image)

        root.setOnClickListener {
            product.id?.let { it1 -> onClickProduct?.invoke(it1) }
        }
    }
}