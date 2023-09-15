package com.example.slide04

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : Activity(), AdapterView.OnItemSelectedListener {
    private lateinit var selection: TextView
    private val items = arrayOf(
        "Android", "IPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"
    )

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.spinner)

        selection = findViewById(R.id.selection)
        val spin: Spinner = findViewById(R.id.spinner)
        spin.onItemSelectedListener = this

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = aa


    }

    override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
        selection.text = items[position]
        Log.d("fdas", "fdasfadfaf")
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        selection.text = ""
    }
}

