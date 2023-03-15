package com.example.onlineshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.databinding.LatestItemBinding
import com.example.onlineshop.models.ProductModel

class LatestRecyclerAdapter(private val products: List<ProductModel>) :
    RecyclerView.Adapter<LatestRecyclerAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding =
            LatestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(products[position])
    }

    class ProductHolder(private val binding: LatestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductModel) {
            binding.name.text = item.name
            binding.category.text = item.category
            binding.price.text =
                String.format(itemView.resources.getString(R.string.price), item.price)
            binding.backgroundImg.setImageBitmap(item.image)
        }
    }
}