package com.example.go.webstockapp.ui.home

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class MyDateTimePickerDialog(
    private val fragmentManager: FragmentManager
) {
    private var onDateTimeSetListener: OnDateTimeSetListener? = null
    private val onDateSetListener by lazy {
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            MyTimePickerDialog(checkNotNull(onDateTimeSetListener)).apply {
                setDate(year, month, dayOfMonth)
            }.show(fragmentManager, null)
        }
    }

    fun show() {
        MyDatePickerDialog(onDateSetListener).show(fragmentManager, null)
    }

    fun setOnDateTimeSetListener(listener: OnDateTimeSetListener) {
        onDateTimeSetListener = listener
    }

    interface OnDateTimeSetListener {
        fun onDateTimeSet(calendar: Calendar)
    }

    class MyDatePickerDialog(private val onDateSetListener: DatePickerDialog.OnDateSetListener) :
        DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            return DatePickerDialog(checkNotNull(context), onDateSetListener, year, month, day)
        }
    }

    class MyTimePickerDialog(private val onDateTimeSetListener: OnDateTimeSetListener) :
        DialogFragment() {

        private var year: Int? = null
        private var month: Int? = null
        private var dayOfMonth: Int? = null

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
                onDateTimeSetListener.onDateTimeSet(c)
            }

            return TimePickerDialog(context, onTimeSetListener, hour, minute, true)
        }

        fun setDate(year: Int, month: Int, dayOfMonth: Int) {
            this.year = year
            this.month = month
            this.dayOfMonth = dayOfMonth
        }
    }
}