package com.example.slide06.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun PopupMenu() {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column {
        Card {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .padding(16.dp),
            ) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(text = { Text("Refresh") }, onClick = {
                        Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                        expanded = false;
                    })
                }
                Text(text = "PopupMenu")
            }
        }
    }
}

@Composable
fun NestedPopupMenu() {
    var expanded by remember { mutableStateOf(false) }
    Card {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .padding(16.dp),
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                PopupMenu()
                PopupMenu()
                PopupMenu()
                PopupMenu()
            }
            Text(text = "PopupMenu")
        }
    }
}