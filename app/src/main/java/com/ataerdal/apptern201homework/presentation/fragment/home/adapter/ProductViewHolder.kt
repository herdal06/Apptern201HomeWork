package com.ataerdal.apptern201homework.presentation.fragment.home.adapter

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.ataerdal.apptern201homework.databinding.ItemProductBinding
import com.ataerdal.apptern201homework.domain.uimodel.Product
import com.ataerdal.apptern201homework.utils.extension.loadImage
import com.ataerdal.apptern201homework.utils.extension.prependDollarSign

class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val onClickProduct: ((productId: Int) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) = binding.apply {
        tvProductName.text = product.name
        tvProductType.text = product.type
        tvOldPrice.text = product.oldPrice.toString().prependDollarSign()
        tvNewPrice.text = product.newPrice.toString().prependDollarSign()
        ivProduct.loadImage(product.image)

        tvOldPrice.paintFlags = tvOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        root.setOnClickListener {
            product.id?.let { it1 -> onClickProduct?.invoke(it1) }
        }
    }
}