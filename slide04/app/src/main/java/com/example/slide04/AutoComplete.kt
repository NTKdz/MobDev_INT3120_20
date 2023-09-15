package com.example.slide04

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView

class AutoComplete : Activity(), TextWatcher {
    private lateinit var selection: TextView
    private lateinit var edit: AutoCompleteTextView
    private val items = arrayOf(
        "Android", "IPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.autocomplete)

        selection = findViewById(R.id.selection)
        edit = findViewById(R.id.edit)
        edit.addTextChangedListener(this)
        edit.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items))
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        selection.text = edit.text
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}
