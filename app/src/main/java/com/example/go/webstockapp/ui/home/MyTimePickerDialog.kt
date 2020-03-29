package com.example.go.webstockapp.ui.home

import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyTimePickerDialog : DialogFragment() {

    private var year: Int? = null
    private var month: Int? = null
    private var dayOfMonth: Int? = null
    private var listener: OnDateTimeSetListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, h, m ->
            val c = Calendar.getInstance().apply {
                set(
                    checkNotNull(year),
                    checkNotNull(month),
                    checkNotNull(dayOfMonth),
                    h,
                    m,
                    0
                )
            }
            listener?.onDateTimeSet(c.timeInMillis)
        }

        return TimePickerDialog(context, onTimeSetListener, hour, minute, true)
    }

    fun setDate(year: Int, month: Int, dayOfMonth: Int) {
        this.year = year
        this.month = month
        this.dayOfMonth = dayOfMonth
    }

    fun setOnDateTimeSetListener(listener: OnDateTimeSetListener) {
        this.listener = listener
    }

    interface OnDateTimeSetListener {
        fun onDateTimeSet(timeInMillis: Long)
    }
}