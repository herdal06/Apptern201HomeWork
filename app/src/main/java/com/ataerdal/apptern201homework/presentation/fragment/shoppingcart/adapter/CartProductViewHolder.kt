package com.ataerdal.apptern201homework.presentation.fragment.shoppingcart.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ataerdal.apptern201homework.databinding.ItemCartProductBinding
import com.ataerdal.apptern201homework.domain.uimodel.Product
import com.ataerdal.apptern201homework.utils.extension.loadImage
import com.ataerdal.apptern201homework.utils.extension.prependDollarSign

class CartProductViewHolder(
    private val binding: ItemCartProductBinding,
    private val onClickProduct: ((productId: Int) -> Unit)?,
    private val onClickDeleteIcon: ((productId: Int) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) = binding.apply {

        tvProductName.text = product.name
        tvProductPrice.text = product.newPrice.toString().prependDollarSign()
        ivProduct.loadImage(product.image)

        root.setOnClickListener {
            product.id?.let { it1 -> onClickProduct?.invoke(it1) }
        }

        ibDeleteFromBasket.setOnClickListener {
            product.id?.let { it1 -> onClickDeleteIcon?.invoke(it1) }
        }
    }
}