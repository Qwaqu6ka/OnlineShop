package com.example.onlineshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.categories.CategoryList
import com.example.onlineshop.databinding.CategoryItemBinding
import com.example.onlineshop.models.CategoryModel

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    private val categories = CategoryList().getList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categories[position])
    }

    class CategoryHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryModel) {
            binding.name.text = item.name
            binding.icon.setImageResource(item.icon)
        }
    }
}