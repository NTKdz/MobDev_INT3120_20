package com.example.slide12.components

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Sensor() {
//    MotionSensor()
    ProximitySensor()
    EnvironmentSensor()
}

@Composable
fun MotionSensor() {
    val sensorManager =
        LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val sensorStatusX = remember {
        mutableStateOf("")
    }
    val sensorStatusY = remember {
        mutableStateOf("")
    }
    val sensorStatusZ = remember {
        mutableStateOf("")
    }

    val accelerometerSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                sensorStatusX.value = event.values[0].toString()
                sensorStatusY.value = event.values[1].toString()
                sensorStatusZ.value = event.values[2].toString()
            }
        }
    }

    sensorManager.registerListener(
        accelerometerSensorEventListener,
        sensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    Column {
        Text(text = "Acceleration force along the x axis" + sensorStatusX.value)
        Text(text = "Acceleration force along the y axis" + sensorStatusY.value)
        Text(text = "Acceleration force along the z axis" + sensorStatusZ.value)
    }
}

@Composable
fun ProximitySensor() {
    val ctx = LocalContext.current
    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val proximitySensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    val sensorStatus = remember {
        mutableStateOf("")
    }

    val proximitySensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0f) {
                    sensorStatus.value = "Near"
                } else {
                    sensorStatus.value = "Away"
                }
            }
        }
    }

    sensorManager.registerListener(
        proximitySensorEventListener,
        proximitySensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Object is",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            modifier = Modifier.padding(5.dp)
        )

        Text(
            text = sensorStatus.value,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            modifier = Modifier.padding(5.dp)
        )

        Text(
            text = "Sensor",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun EnvironmentSensor() {
    val context = LocalContext.current
    var sensorStatus by remember { mutableStateOf("") }

    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    val lightSensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            sensorStatus = event.values[0].toString()
            Log.d("temp1", event.values[0].toString())
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    sensorManager.registerListener(
        lightSensorEventListener,
        lightSensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Light: $sensorStatus",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            modifier = Modifier.padding(5.dp)
        )
    }
}
