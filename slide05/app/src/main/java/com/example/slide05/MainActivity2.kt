package com.example.slide05

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import java.text.DateFormat
import java.util.Calendar

class MainActivity2 : Activity() {
    private val fmtDateAndTime = DateFormat.getDateTimeInstance()
    private lateinit var lblDateAndTime: TextView
    private val myCalendar = Calendar.getInstance()

    private val d = DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, monthOfYear)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateLabel()
    }

    private val t = TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
        myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        myCalendar.set(Calendar.MINUTE, minute)
        updateLabel()
    }

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.date_and_time)
        lblDateAndTime = findViewById(R.id.lblDateAndTime)
        val btnDate = findViewById<Button>(R.id.btnDate)
        btnDate.setOnClickListener {
            DatePickerDialog(
                this@MainActivity2, d,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
        val btnTime = findViewById<Button>(R.id.btnTime)
        btnTime.setOnClickListener {
            TimePickerDialog(
                this@MainActivity2, t,
                myCalendar[Calendar.HOUR_OF_DAY],
                myCalendar[Calendar.MINUTE], true
            ).show()
        }
        updateLabel()
    }

    private fun updateLabel() {
        lblDateAndTime.text = fmtDateAndTime.format(myCalendar.time)
    }
}
