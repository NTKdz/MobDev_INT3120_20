package com.example.slide12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.slide12.components.Camera
import com.example.slide12.components.Sensor
import com.example.slide12.components.Telephone
import com.example.slide12.components.Wifi
import com.example.slide12.ui.theme.Slide12Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Slide12Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var current by remember { mutableStateOf("") }
    Column {
        Row(horizontalArrangement = Arrangement.Center) {
            Button(onClick = { current = "sensor" }) {
                Text(text = "Sensors")
            }
            Button(onClick = { current = "wifi" }) {
                Text(text = "Wifi")
            }
            Button(onClick = { current = "telephone" }) {
                Text(text = "Telephone")
            }
            Button(onClick = { current = "camera" }) {
                Text(text = "camera")
            }
        }

        when (current) {
            "sensor" -> Sensor()
            "wifi" -> Wifi()
            "telephone" -> Telephone()
            "camera" -> Camera()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Slide12Theme {
        Greeting("Android")
    }
}