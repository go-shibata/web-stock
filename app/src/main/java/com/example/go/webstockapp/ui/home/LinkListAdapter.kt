package com.example.go.webstockapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.go.webstockapp.databinding.ItemLinkListBinding

class LinkListAdapter(
    owner: LifecycleOwner,
    private val viewModel: HomeViewModel
) : RecyclerView.Adapter<LinkListAdapter.ViewHolder>() {

    init {
        viewModel.links
            .observe(owner, Observer { notifyDataSetChanged() })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLinkListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = viewModel.links.value?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = viewModel.links.value?.get(position)
        holder.apply {
            binding.link = data
        }
    }

    inner class ViewHolder(val binding: ItemLinkListBinding) : RecyclerView.ViewHolder(binding.root)
}