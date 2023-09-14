package com.example.slide04

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.TextView

class GridView : Activity(), AdapterView.OnItemClickListener {
    private lateinit var selection: TextView
    private val items = arrayOf(
        "Android", "IPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7",
        "Max OS X"
    )

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.gridview)
        selection = findViewById(R.id.selection)
        val gv = findViewById<GridView>(R.id.grid)
        val aa = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        gv.adapter = aa
        gv.setOnItemClickListener(this)
    }

    override fun onItemClick(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
        selection.text = items[position]
    }
}
