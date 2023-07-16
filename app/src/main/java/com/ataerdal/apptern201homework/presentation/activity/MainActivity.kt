package com.ataerdal.apptern201homework.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ataerdal.apptern201homework.R
import com.ataerdal.apptern201homework.base.listener.ShoppingCartListener
import com.ataerdal.apptern201homework.databinding.ActivityMainBinding
import com.ataerdal.apptern201homework.presentation.fragment.home.HomeFragment
import com.ataerdal.apptern201homework.presentation.fragment.productdetail.ProductDetailFragment
import com.ataerdal.apptern201homework.presentation.fragment.shoppingcart.ShoppingCartFragment
import com.ataerdal.apptern201homework.presentation.fragment.storelocations.StoreLocationsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , ShoppingCartListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ibBasketClickListener()
        ibBackClickListener()
    }

    private fun ibBackClickListener() = binding.ibBack.setOnClickListener {
        when (supportFragmentManager.findFragmentById(R.id.fragmentContainerView)) {
            is ProductDetailFragment -> {
                supportFragmentManager.popBackStack()
                navigateToFragment(HomeFragment())
            }
            is StoreLocationsFragment -> {
                supportFragmentManager.popBackStack()
                navigateToFragment(ProductDetailFragment())
            }
            else -> onBackPressed()
        }
    }

    private fun ibBasketClickListener() = binding.ibBasket.setOnClickListener {
        navigateToFragment(ShoppingCartFragment())
    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCartStatusChanged(hasProducts: Boolean) {
        binding.ivHasProduct.isVisible = hasProducts
    }
}