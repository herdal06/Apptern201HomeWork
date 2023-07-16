package com.ataerdal.apptern201homework.presentation.fragment.storelocations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ataerdal.apptern201homework.base.BaseFragment
import com.ataerdal.apptern201homework.databinding.FragmentStoreLocationsBinding
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreLocationsFragment : BaseFragment<FragmentStoreLocationsBinding>() {

    private var savedInstanceState: Bundle? = null

    override fun initialize() {
        binding?.mapView?.onCreate(savedInstanceState)

        binding?.mapView?.getMapAsync {

        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentStoreLocationsBinding {
        this.savedInstanceState = savedInstanceState
        return FragmentStoreLocationsBinding.inflate(inflater, container, false)
    }

    private fun getLocations(): List<LatLng> {
        val locations = mutableListOf<LatLng>()
        locations.add(LatLng(8.8566, 2.3522))

        return locations
    }

    override fun onResume() {
        super.onResume()
        binding?.mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding?.mapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding?.mapView?.onDestroy()
    }

}