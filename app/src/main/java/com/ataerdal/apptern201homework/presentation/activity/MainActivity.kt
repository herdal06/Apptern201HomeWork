package com.ataerdal.apptern201homework.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ataerdal.apptern201homework.R
import com.ataerdal.apptern201homework.databinding.ActivityMainBinding
import com.ataerdal.apptern201homework.presentation.fragment.shoppingcart.ShoppingCartFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ibBasketClickListener()
    }

    private fun ibBackClickListener() = binding.ibBack.setOnClickListener {

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
        return navController.navigateUp()
    }
}