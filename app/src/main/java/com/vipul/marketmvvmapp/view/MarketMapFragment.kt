package com.vipul.marketmvvmapp.view

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vipul.marketmvvmapp.R
import com.vipul.marketmvvmapp.adapters.MapCustomInfoWindowAdapter
import com.vipul.marketmvvmapp.repository.MarketRepository
import com.vipul.marketmvvmapp.retrofit.RetrofitClientInstance
import com.vipul.marketmvvmapp.utils.Utils
import com.vipul.marketmvvmapp.viewmodel.MarketViewModel
import com.vipul.marketmvvmapp.viewmodel.MarketViewModelFactory
import kotlinx.android.synthetic.main.fragment_market_map.*


class MarketMapFragment : Fragment(), OnMapReadyCallback {

    lateinit var marketViewModel: MarketViewModel
    private val retrofitService = RetrofitClientInstance.getRetrofitInstance()
    lateinit var myGoogleMap: GoogleMap
    lateinit var progressDialog: ProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpViewModel()
        return inflater.inflate(R.layout.fragment_market_map, container, false)
    }

    private fun setUpViewModel() {
        marketViewModel =
            ViewModelProvider(this, MarketViewModelFactory(MarketRepository(retrofitService))).get(
                MarketViewModel::class.java
            )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        myGoogleMap = googleMap
        myGoogleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        myGoogleMap.uiSettings.isZoomControlsEnabled = true
        myGoogleMap.uiSettings.isCompassEnabled = true
        myGoogleMap.uiSettings.isMyLocationButtonEnabled = false
        myGoogleMap.uiSettings.setAllGesturesEnabled(true)
        myGoogleMap.uiSettings.isMapToolbarEnabled = false


        this.myGoogleMap.setInfoWindowAdapter(MapCustomInfoWindowAdapter(requireContext()))

        setUpMarkerData(this.myGoogleMap)

    }

    private fun setUpMarkerData(myGoogleMap: GoogleMap) {
        marketViewModel.movieList.observe(viewLifecycleOwner) {
            it.forEach {
                val markerOption = MarkerOptions().icon(
                    Utils.bitmapDescriptorFromVector(
                        requireContext(),
                        R.drawable.ic_man
                    )
                ).run {
                    position(LatLng(it.lat, it.lang))
                }

                val marker = myGoogleMap.addMarker(markerOption)
                marker?.tag = it
            }

            myGoogleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(it[0].lat, it[0].lang),
                    14F
                )
            )
        }

        marketViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        marketViewModel.currentState.observe(viewLifecycleOwner) {
            if (it.equals(Utils.LOADING)) {
                progressDialog =
                    ProgressDialog.show(requireContext(), "Loading", "Loading Please Wait")
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        }

        marketViewModel.getMarketList()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }


}