package com.example.slide11

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slide11.ui.theme.Slide11Theme

class MainActivity : ComponentActivity() {

    private lateinit var mService: MyService
    private var mBound by mutableStateOf(false)

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as MyService.LocalBinder
            mService = binder.getService()
            mBound = true
            Log.d("conee", "fdsfafadfdfdsafdsa$mBound")
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    var iRemoteService: IRemoteService? = null

    val mConnection = object : ServiceConnection {

        // Called when the connection with the service is established.
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // Following the preceding example for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service.
            iRemoteService = IRemoteService.Stub.asInterface(service)
        }

        // Called when the connection with the service disconnects unexpectedly.
        override fun onServiceDisconnected(className: ComponentName) {
            Log.e("TAG", "Service has unexpectedly disconnected")
            iRemoteService = null
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MyService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
            Toast.makeText(this, "connect", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Slide11Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (mBound) {
                        Greeting("Android", mService, mBound = mBound)
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }

}

@Composable
fun Greeting(name: String, mService: MyService, mBound: Boolean) {
    val serviceStatus = remember {
        mutableStateOf(false)
    }
    val buttonValue = remember {
        mutableStateOf("Start Service")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Services",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (serviceStatus.value) {
                serviceStatus.value = !serviceStatus.value
                buttonValue.value = "Start Service"
                context.stopService(Intent(context, MyService::class.java))
            } else {
                serviceStatus.value = !serviceStatus.value
                buttonValue.value = "Stop Service"
                context.startService(Intent(context, MyService::class.java))
            }
        }) {
            Text(
                text = buttonValue.value,
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Button(onClick = {
            if (mBound) {
                val num: Int = mService.randomNumber
                Toast.makeText(context, "number: $num", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "bind")
        }
//        Button(onClick = {
//            val text = "Hello toast!"
//            val duration = Toast.LENGTH_SHORT
//
//            val toast = Toast.makeText(context, text, duration)
//            toast.show()
//        }) {
//            Text(
//                text = "notification",
//                modifier = Modifier.padding(10.dp),
//                color = Color.White,
//                fontSize = 20.sp
//            )
//        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Slide11Theme {

    }
}