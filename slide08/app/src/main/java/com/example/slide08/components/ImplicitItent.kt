package com.example.slide08.components

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.example.slide08.GreetingActivity

@Composable
fun ImplicitIntent() {
    val context = LocalContext.current
    val googleIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))

    Button(
        onClick = {
            startActivity(context, googleIntent, null)
        }, modifier = Modifier.fillMaxWidth()
    ) {
        Text("google")
    }

    val searchIntent = Intent(Intent.ACTION_WEB_SEARCH)
    searchIntent.putExtra(SearchManager.QUERY, "Hello world!")

    Button(
        onClick = { startActivity(context, searchIntent, null) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "search google")
    }
    val smsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:01234124"))

    Button(
        onClick = {
            startActivity(context, smsIntent, null)
        }, modifier = Modifier.fillMaxWidth()
    ) {
        Text("sms")
    }

    val dialIntent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:04123412"))

    Button(
        onClick = {
            startActivity(context, dialIntent, null)
        }, modifier = Modifier.fillMaxWidth()
    ) {
        Text("dial")
    }
}
