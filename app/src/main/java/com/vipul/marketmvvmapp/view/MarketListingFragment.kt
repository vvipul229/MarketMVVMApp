package com.vipul.marketmvvmapp.view

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vipul.marketmvvmapp.R
import com.vipul.marketmvvmapp.adapters.MarketListRecyclerViewAdapter
import com.vipul.marketmvvmapp.repository.MarketRepository
import com.vipul.marketmvvmapp.retrofit.RetrofitClientInstance
import com.vipul.marketmvvmapp.utils.Utils
import com.vipul.marketmvvmapp.viewmodel.MarketViewModel
import com.vipul.marketmvvmapp.viewmodel.MarketViewModelFactory
import kotlinx.android.synthetic.main.fragment_market_listing.*

class MarketListingFragment : Fragment() {

    lateinit var marketViewModel: MarketViewModel
    private val retrofitService = RetrofitClientInstance.getRetrofitInstance()
    private val adapter = MarketListRecyclerViewAdapter()
    lateinit var progressDialog: ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpViewModel()
        return inflater.inflate(R.layout.fragment_market_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
    }

    private fun setUpViewModel() {
        marketViewModel =
            ViewModelProvider(this, MarketViewModelFactory(MarketRepository(retrofitService))).get(
                MarketViewModel::class.java
            )
    }


    private fun setupRecyclerview() {
        rvMarketList.layoutManager = LinearLayoutManager(context)
        rvMarketList.adapter = adapter
        rvMarketList.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        marketViewModel.movieList.observe(viewLifecycleOwner) {
            adapter.setMarketList(it)
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
}