package com.example.slide04
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListView : AppCompatActivity() {
    private val mobileArray = arrayOf(
        "Android", "IPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listview)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mobileArray)

        val listView = findViewById<ListView>(R.id.mobile_list)
        listView.adapter = adapter
    }
}
