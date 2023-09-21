package com.example.slide07.components

import android.app.Activity
import android.app.ListActivity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.slide07.GreetingActivity

@Composable
fun ImplicitIntent() {
    val context = LocalContext.current
    val googleIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))

    Button(onClick = {
        startActivity(context, googleIntent, null)
    }
    ) {
        Text("google")
    }

    val smsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:01234124"))

    Button(onClick = {
        startActivity(context, smsIntent, null)
    }
    ) {
        Text("sms")
    }

    val dialIntent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:04123412"))

    Button(onClick = {
        startActivity(context, dialIntent, null)
    }
    ) {
        Text("dial")
    }
}

