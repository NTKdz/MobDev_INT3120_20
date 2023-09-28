package com.example.slide09.components

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InternalSto() {
    val message = remember {
        mutableStateOf("")
    }
    val txtMsg = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Internal Storage in Android",
        )

        TextField(
            value = message.value,
            onValueChange = { message.value = it },
            placeholder = { Text(text = "Enter your message") },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
        )

        Button(
            onClick = {
                try {
                    val fos: FileOutputStream =
                        context.openFileOutput("demoFile.txt", Context.MODE_PRIVATE)
                    fos.write(message.value.toByteArray())
                    fos.flush()
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                message.value = ""
                Toast.makeText(context, "Data saved successfully..", Toast.LENGTH_SHORT).show()

            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Write Data to Internal Storage")
        }

        Text(
            text = "Data will appear below : \n" + txtMsg.value,
        )

        Button(
            onClick = {
                try {
                    val fin: FileInputStream = context.openFileInput("demoFile.txt")
                    var a: Int
                    val temp = StringBuilder()
                    while (fin.read().also { a = it } != -1) {
                        temp.append(a.toChar())
                    }

                    txtMsg.value = temp.toString()
                    fin.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Read Data from Internal Storage")
        }
    }


}