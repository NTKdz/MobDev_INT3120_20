package com.example.slide05

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TabHost

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab)

        val tabs = findViewById<TabHost>(R.id.tabhost)
        tabs.setup()

        var spec = tabs.newTabSpec("tag1")
        spec.setContent(R.id.tab1)
        spec.setIndicator("1-Clock")
        tabs.addTab(spec)

        spec = tabs.newTabSpec("tag2")
        spec.setContent(R.id.tab2)
        spec.setIndicator("2-Login")
        tabs.addTab(spec)

        tabs.currentTab = 0

        val btnGo = findViewById<Button>(R.id.btnGo)
        btnGo.setOnClickListener(View.OnClickListener {
            val txtPerson = findViewById<EditText>(R.id.txtPerson)
            val theUser = txtPerson.text.toString()
            txtPerson.setText("Hola $theUser")
        })
    }
}
