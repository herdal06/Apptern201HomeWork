package com.ataerdal.apptern201homework.presentation.fragment.home.adapter

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.ataerdal.apptern201homework.databinding.ItemProductBinding
import com.ataerdal.apptern201homework.domain.uimodel.Product
import com.ataerdal.apptern201homework.utils.extension.loadImage

class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val onClickProduct: ((product: Product) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) = binding.apply {
        tvProductName.text = product.name
        tvProductType.text = product.type
        tvOldPrice.text = "$${product.oldPrice}"
        tvNewPrice.text = "$${product.newPrice}"
        ivProduct.loadImage(product.image)

        tvOldPrice.paintFlags = tvOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        root.setOnClickListener {
            onClickProduct?.invoke(product)
        }
    }
}