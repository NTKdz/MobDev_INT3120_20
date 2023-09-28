package com.example.slide08

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.slide08.components.ImplicitIntent
import com.example.slide08.components.SendMessageLayout
import com.example.slide08.ui.theme.Slide08Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val receiver = BroadcastReceiver();
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerReceiver(receiver, IntentFilter("TEST_ACTION2"))
        setContent {
            Slide08Theme {
                val context = LocalContext.current

                // A surface container using the 'background' color from the theme
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            text = { Text("Show snackbar") },
                            icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                            onClick = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Snackbar")
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {

                        SendMessageLayout()

                        ImplicitIntent()

                        Button(onClick = {
                            Intent().also { intent ->
                                intent.action = "TEST_ACTION2"
                                intent.putExtra("data", "Nothing to see here, move along.")
                                sendBroadcast(intent)
                            }
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Send Broadcast")
                        }

                    }
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        Log.d("out","unregister")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Slide08Theme {
        Greeting("Android")
    }
}