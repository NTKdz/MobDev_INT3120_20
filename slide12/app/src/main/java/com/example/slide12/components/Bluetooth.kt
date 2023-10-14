package com.example.slide12.components

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp

@Composable
fun Bluetooth() {
    val context = LocalContext.current
    val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val bluetoothAdapter = bluetoothManager.adapter
    if (bluetoothAdapter == null) {
        Log.d("bluetooth", "null")
    } else {
        var isOn by remember { mutableStateOf(bluetoothAdapter.isEnabled) }
        var discoverability by remember {
            mutableStateOf(
                when (try {
                    bluetoothAdapter.scanMode
                } catch (_: SecurityException) {
                    BluetoothAdapter.SCAN_MODE_NONE
                }
                ) {
                    BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE -> "Is Discoverable"
                    BluetoothAdapter.SCAN_MODE_CONNECTABLE -> "Is Connectable"
                    else -> "None"
                }
            )
        }

        val bluetoothReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val action = intent.action

                if (action == BluetoothAdapter.ACTION_STATE_CHANGED) {
                    isOn = intent.getIntExtra(
                        BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR
                    ) == BluetoothAdapter.STATE_ON
                } else if (action == BluetoothAdapter.ACTION_SCAN_MODE_CHANGED) {
                    discoverability = when (intent.getIntExtra(
                        BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.ERROR
                    )) {
                        BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE -> "Is Discoverable"
                        BluetoothAdapter.SCAN_MODE_CONNECTABLE -> "Is Connectable"
                        else -> "None"
                    }
                }
            }
        }

        DisposableEffect(Unit) {
            context.registerReceiver(
                bluetoothReceiver,
                IntentFilter().apply {
                    addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)
                    addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
                }
            )

            onDispose {
                context.unregisterReceiver(bluetoothReceiver)
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Button(
                    onClick = {
                        try {
                            context.startActivity(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
                        } catch (_: SecurityException) {
                            Log.e("Bluetooth", "Can't turn on")
                        }
                    },
                    enabled = !isOn
                ) { Text(text = "Turn on") }

                Button(
                    onClick = {
                        try {
                            context.startActivity(
                                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
                                    putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 10)
                                }
                            )
                        } catch (_: SecurityException) {
                            Log.e("Bluetooth", "Can't discover")
                        }
                    },
                    enabled = discoverability != "Is Discoverable"
                ) { Text(text = "turn Discover") }
            }

            Text(
                text = discoverability,
            )
        }
    }
}