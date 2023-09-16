package com.example.slide04

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.slide04.CustomSpinner

class MainActivity : Activity(), AdapterView.OnItemSelectedListener,
    CustomSpinner.OnSpinnerEventsListener {
    private lateinit var selection: TextView
    private lateinit var customSpinner: CustomSpinner
    private val items = arrayOf(
        "Android", "IPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"
    )
    private var selected: Boolean = false;

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.spinner)

        selection = findViewById(R.id.selection)
        customSpinner = findViewById(R.id.spinner)
        customSpinner.setSpinnerEventsListener(this)

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        customSpinner.adapter = aa

        customSpinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
        selection.text = items[position]
        selected = true;
        Log.d("ItemSelection", "Item selected: ${items[position]}")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selection.text = ""
    }

    override fun onSpinnerOpened(spin: CustomSpinner?) {
        Log.d("CustomSpinner", "Spinner opened")
    }

    override fun onSpinnerClosed(spin: CustomSpinner?) {
        if (!selected) {
            Log.d("CustomSpinner", "Spinner not")
            selection.text = "null"
        }
        selected = false
    }
}
