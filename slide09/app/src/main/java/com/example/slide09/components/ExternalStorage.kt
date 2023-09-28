package com.example.slide09.components

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import android.os.Environment
import android.Manifest
import androidx.compose.foundation.layout.wrapContentHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExternalSto() {
    val message = remember {
        mutableStateOf("")
    }
    val fileName = remember {
        mutableStateOf("")
    }
    val txtMsg = remember {
        mutableStateOf("")
    }
    val path = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "External Storage in Android",
        )

        TextField(
            value = message.value,
            onValueChange = { message.value = it },
            placeholder = { Text(text = "Enter your message") },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
        )

        TextField(
            value = fileName.value,
            onValueChange = { fileName.value = it },
            placeholder = { Text(text = "Enter your file name") },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
        )

        Button(
            onClick = {
                try {
                    ActivityCompat.requestPermissions(
                        context as Activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 23
                    )

                    val folder: File =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

                    val file = File(folder, fileName.value + "txt")
                    writeTextData(file, message.value, context)
                    message.value = ""
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Write Data to External Storage")
        }

//        Button(
//            onClick = {
//                val folder: File? = context.getExternalFilesDir("private")
//                val file = File(folder, "demo.txt")
//                writeTextData(file, message.value, context)
//
//                message.value = ""
//                Toast.makeText(context, "Data saved privately", Toast.LENGTH_SHORT).show()
//
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "Save Privately")
//        }

        Text(
            text = "Data will appear below : \n" + txtMsg.value,
        )
        Text(text = "path: " + path.value)
        Button(
            onClick = {
                val folder =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val file = File(folder, fileName.value + ".txt")
                val data: String = getdata(file)
                if (data != "") {
                    txtMsg.value = getdata(file)
                } else {
                    txtMsg.value = "No Data Found"
                }
                path.value = file.absolutePath;
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "View")
        }

//        Button(
//            onClick = {
//                val folder: File? = context.getExternalFilesDir("private")
//                val file = File(folder, "demo.txt")
//                val data = getdata(file)
//                if (data != null) {
//                    txtMsg.value = data
//                } else {
//                    txtMsg.value = "No Data Found"
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "View\nPrivate", textAlign = TextAlign.Center)
//        }
    }
}


private fun isExternalStorageWritable(): Boolean {
    val state = Environment.getExternalStorageState()
    return Environment.MEDIA_MOUNTED == state
}

private fun isExternalStorageReadable(): Boolean {
    val state = Environment.getExternalStorageState()
    return Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state
}

private fun getdata(myfile: File): String {
    var fileInputStream: FileInputStream? = null
    try {
        fileInputStream = FileInputStream(myfile)
        var i = -1
        val buffer = StringBuffer()
        while (fileInputStream.read().also { i = it } != -1) {
            buffer.append(i.toChar())
        }
        return buffer.toString()
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    } finally {
        if (fileInputStream != null) {
            try {
                fileInputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    return ""
}

private fun writeTextData(file: File, data: String, context: Context) {
    var fileOutputStream: FileOutputStream? = null
    try {
        fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(data.toByteArray())
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
