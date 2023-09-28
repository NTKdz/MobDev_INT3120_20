package com.example.slide08

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class GetMessage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var intent : Intent = this.intent
        var value = intent.getStringExtra(Intent.EXTRA_TEXT)

        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Received Message",
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (value != null) {
                    Text(
                        text = "hello bro $value!",
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                } else {
                    Text(
                        text = "nothing",
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
                Button(onClick = {
                    setResult(RESULT_OK, Intent().putExtra("feedback", value))
                    onBackPressedDispatcher.onBackPressed()
                }) {
                    Text(text = "Go back")
                }
            }
        }
    }
}