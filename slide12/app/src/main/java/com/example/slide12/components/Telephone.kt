package com.example.slide12.components

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.provider.Telephony
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivities
import androidx.core.content.ContextCompat.startActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Telephone() {
    val context = LocalContext.current

    var sender by remember { mutableStateOf<String?>("") }
    var message by remember { mutableStateOf("") }

    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("sms:5532532324"))
    intent.putExtra("sms_body", "please send to send me")
    startActivity(context, intent, null)

    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Telephony.Sms.Intents
                .getMessagesFromIntent(intent)
                .forEach {
                    sender = it.originatingAddress
                    message = it.messageBody
                }
        }
    }

    context.registerReceiver(receiver, IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION))

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = sender ?: "Unknown",
            onValueChange = {},
            enabled = false
        )

        TextField(
            value = message,
            onValueChange = {},
            enabled = false
        )
    }
}