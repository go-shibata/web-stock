package com.example.go.webstockapp.ui.notifications

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.go.webstockapp.R
import com.example.go.webstockapp.databinding.FragmentNotificationsBinding
import com.example.go.webstockapp.di.ViewModelFactory
import com.example.go.webstockapp.ui.MyDateTimePickerDialog
import com.example.go.webstockapp.ui.MyDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NotificationsFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory<NotificationsViewModel>

    private lateinit var binding: FragmentNotificationsBinding

    private val viewModel: NotificationsViewModel by activityViewModels { factory }
    private val onClickNotificationListener =
        object : NotificationsEpoxyController.OnClickNotificationListener {
            override fun onClickNotification(notification: NotificationAndLink) {
                val builder = AlertDialog.Builder(requireContext())
                    .setTitle(
                        getString(
                            R.string.click_notification_title,
                            notification.getLink().title
                        )
                    )
                    .setItems(R.array.click_notification_list) { dialog, which ->
                        when (which) {
                            0 -> editNotification(notification)
                            1 -> viewModel.deleteNotification(notification)
                        }
                        dialog.dismiss()
                    }
                    .setNegativeButton(R.string.cancel, null)
                MyDialogFragment()
                    .setBuilder(builder)
                    .show(parentFragmentManager, null)
            }
        }
    private val notificationListController =
        NotificationsEpoxyController(onClickNotificationListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.notificationList.apply {
            setController(notificationListController)
        }.requestModelBuild()

        viewModel.notifications.observe(this.viewLifecycleOwner, Observer {
            notificationListController.setData(it)
        })
        return binding.root
    }

    fun editNotification(notification: NotificationAndLink) {
        val onDateTimeSetListener = object : MyDateTimePickerDialog.OnDateTimeSetListener {
            override fun onDateTimeSet(calendar: Calendar) {
                Snackbar.make(
                    binding.root,
                    getString(
                        R.string.notification_create_message,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE)
                    ),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.editNotification(notification, calendar.timeInMillis)
            }
        }
        MyDateTimePickerDialog(
            parentFragmentManager
        ).apply {
            setOnDateTimeSetListener(onDateTimeSetListener)
        }.show()
    }
}
