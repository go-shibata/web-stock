package com.example.go.webstockapp.ui.home

import com.airbnb.epoxy.EpoxyController
import com.example.go.webstockapp.R
import com.example.go.webstockapp.database.entity.Link
import com.example.go.webstockapp.itemLinkList
import com.example.go.webstockapp.network.faviconUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_link_list.view.*

class HomeEpoxyController(
    val listener: OnClickLinkListener
) : EpoxyController() {

    private var data: List<Link> = emptyList()

    fun setData(data: List<Link>) {
        this.data = data
        requestModelBuild()
    }

    override fun buildModels() {
        data.forEach {
            itemLinkList {
                id(it.id)
                link(it)
                onBind { _, view, _ ->
                    view.dataBinding.root.apply {
                        Picasso.get()
                            .load(it.domain?.faviconUrl())
                            .placeholder(R.drawable.ic_image_gray_24dp)
                            .into(icon)

                        setOnClickListener { _ -> listener.onClickLink(it) }
                    }
                }
            }
        }
    }

    interface OnClickLinkListener {
        fun onClickLink(link: Link)
    }
}