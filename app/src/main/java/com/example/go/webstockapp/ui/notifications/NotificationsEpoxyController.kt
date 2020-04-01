package com.example.go.webstockapp.ui.notifications

import com.airbnb.epoxy.EpoxyController
import com.example.go.webstockapp.R
import com.example.go.webstockapp.itemNotificationList
import com.example.go.webstockapp.network.faviconUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_link_list.view.*

class NotificationsEpoxyController(
    val listener: OnClickNotificationListener
) : EpoxyController() {

    private var data: List<NotificationAndLink> = emptyList()

    fun setData(data: List<NotificationAndLink>) {
        this.data = data
        requestModelBuild()
    }

    override fun buildModels() {
        data.forEach {
            itemNotificationList {
                id(it.notification.id)
                notification(it.notification)
                link(it.getLink())
                onBind { _, view, _ ->
                    view.dataBinding.root.apply {
                        Picasso.get()
                            .load(it.getLink().domain?.faviconUrl())
                            .placeholder(R.drawable.ic_image_gray_24dp)
                            .into(icon)

                        setOnClickListener { _ -> listener.onClickNotification(it) }
                    }
                }
            }
        }
    }

    interface OnClickNotificationListener {
        fun onClickNotification(notificationAndLink: NotificationAndLink)
    }
}