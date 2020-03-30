package com.example.go.webstockapp.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.go.webstockapp.R
import com.example.go.webstockapp.databinding.ItemNotificationListBinding
import com.example.go.webstockapp.network.faviconUrl
import com.squareup.picasso.Picasso

class NotificationListAdapter(
    owner: LifecycleOwner,
    private val viewModel: NotificationsViewModel
) : RecyclerView.Adapter<NotificationListAdapter.ViewHolder>() {

    private var listener: OnClickNotificationListener? = null

    init {
        viewModel.notifications
            .observe(owner, Observer { notifyDataSetChanged() })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = viewModel.notifications.value?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = viewModel.notifications.value?.get(position)
        holder.apply {
            binding.notification = data?.notification
            binding.link = data?.getLink()
            Picasso.get()
                .load(data?.getLink()?.domain?.faviconUrl())
                .placeholder(R.drawable.ic_image_gray_24dp)
                .into(binding.icon)

            binding.root.setOnClickListener {
                checkNotNull(data)
                listener?.onClickNotification(data)
            }
        }
    }

    fun setOnClickNotificationListener(listener: OnClickNotificationListener) {
        this.listener = listener
    }

    inner class ViewHolder(val binding: ItemNotificationListBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnClickNotificationListener {
        fun onClickNotification(notification: NotificationAndLink)
    }
}