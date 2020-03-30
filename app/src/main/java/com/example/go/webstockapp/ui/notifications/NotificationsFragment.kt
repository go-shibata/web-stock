package com.example.go.webstockapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.go.webstockapp.database.entity.Notification
import com.example.go.webstockapp.databinding.FragmentNotificationsBinding
import com.example.go.webstockapp.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NotificationsFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory<NotificationsViewModel>

    private val viewModel: NotificationsViewModel by activityViewModels { factory }
    private val onClickNotificationListener =
        object : NotificationListAdapter.OnClickNotificationListener {
            override fun onClickNotification(notification: Notification) {
                TODO()
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.notificationList.apply {
            adapter = NotificationListAdapter(this@NotificationsFragment, viewModel).apply {
                setOnClickNotificationListener(onClickNotificationListener)
            }
            layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }
}
