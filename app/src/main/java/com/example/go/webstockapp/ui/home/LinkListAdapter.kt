package com.example.go.webstockapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.go.webstockapp.R
import com.example.go.webstockapp.databinding.ItemLinkListBinding
import com.example.go.webstockapp.network.faviconUrl
import com.squareup.picasso.Picasso

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
            Picasso.get()
                .load(data?.domain?.faviconUrl())
                .placeholder(R.drawable.ic_image_gray_24dp)
                .into(binding.icon)
        }
    }

    inner class ViewHolder(val binding: ItemLinkListBinding) : RecyclerView.ViewHolder(binding.root)
}