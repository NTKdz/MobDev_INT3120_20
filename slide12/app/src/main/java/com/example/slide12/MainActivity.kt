package com.example.slide12

import android.bluetooth.BluetoothAdapter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.slide12.components.Bluetooth
import com.example.slide12.components.Camera
import com.example.slide12.components.Sensor
import com.example.slide12.components.Telephone
import com.example.slide12.components.Wifi
import com.example.slide12.ui.theme.Slide12Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                android.Manifest.permission.BLUETOOTH_CONNECT
            ) == PackageManager.PERMISSION_DENIED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(android.Manifest.permission.BLUETOOTH_CONNECT),
                    2
                )
                return
            }
        }

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

        }
        Row(horizontalArrangement = Arrangement.Center) {
            Button(onClick = { current = "camera" }) {
                Text(text = "camera")
            }
            Button(onClick = { current = "bluetooth" }) {
                Text(text = "bluetooth")
            }
        }
        when (current) {
            "sensor" -> Sensor()
            "wifi" -> Wifi()
            "telephone" -> Telephone()
            "camera" -> Camera()
            "bluetooth" -> Bluetooth()
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