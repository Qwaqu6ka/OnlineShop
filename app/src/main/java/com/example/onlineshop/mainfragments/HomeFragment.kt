package com.example.onlineshop.mainfragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.BaseFragment
import com.example.onlineshop.MainActivityViewModel
import com.example.onlineshop.R
import com.example.onlineshop.adapters.CategoryAdapter
import com.example.onlineshop.adapters.LatestRecyclerAdapter
import com.example.onlineshop.adapters.SaleRecyclerAdapter
import com.example.onlineshop.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.latestRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.saleRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.categoryRecycler.adapter = CategoryAdapter()
        viewModel.dataIsReady.observe(viewLifecycleOwner) { ready ->
            if (ready) {
                viewModel.latestProductList.observe(viewLifecycleOwner) {
                    if (it.isNotEmpty()) {
                        binding.latestRecycler.adapter = LatestRecyclerAdapter(it)
                        binding.latestRecycler.visibility = View.VISIBLE
                        binding.latestProgress.visibility = View.GONE
                    }
                }
                viewModel.saleProductList.observe(viewLifecycleOwner) {
                    if (it.isNotEmpty()) {
                        binding.saleRecycler.adapter = SaleRecyclerAdapter(it)
                        binding.saleRecycler.visibility = View.VISIBLE
                        binding.saleProgress.visibility = View.GONE
                    }
                }
            }
        }
    }
}