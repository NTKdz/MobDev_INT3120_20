package com.example.slide08.components

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*


@Composable
fun SendIntentBroadcast() {
    val sendIntent = Intent("TEST_ACTION")
    sendIntent.putExtra("message", "hello, this is a broadcast event")
}
