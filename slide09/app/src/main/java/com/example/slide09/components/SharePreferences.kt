package com.example.slide09.components

import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetSharedPreferences() {
    val context = LocalContext.current
    val reference = context.getSharedPreferences("preference", MODE_PRIVATE)
    var value by remember { mutableStateOf(reference.getString("savedText", "nothing")) }

    Column {
        OutlinedTextField(value = value ?: "", onValueChange = { value = it })

        Button(onClick = {
            reference
                .edit()
                .apply {
                    putString("savedText", value)
                    apply()
                }
            Toast.makeText(
                context,
                "saved",
                Toast.LENGTH_SHORT
            ).show()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "save")
        }

        Button(onClick = {
            Toast.makeText(
                context,
                reference.getString("savedText", "nothing"),
                Toast.LENGTH_SHORT
            ).show()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "read")
        }
    }

}