package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.provider.UserDictionary
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.components.MyContentProvider
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Greeting("Android")
                        dictionary()
                    }
                }
            }
        }
    }
}

private val projection: Array<String> = arrayOf(
    UserDictionary.Words._ID,
    UserDictionary.Words.WORD,
    UserDictionary.Words.LOCALE
)

@SuppressLint("Range")
@Composable
fun dictionary() {
    val context = LocalContext.current

    val cursor = context.contentResolver.query(
        UserDictionary.Words.CONTENT_URI,
        null,
        null, null, null
    )

    val text = if (cursor!!.moveToFirst()) {
        val strBuild = StringBuilder()
        while (!cursor.isAfterLast) {
            strBuild.append(
                """
                ${cursor.getString(cursor.getColumnIndex(UserDictionary.Words.WORD))}
                """.trimIndent()
            )
            cursor.moveToNext()
        }
        strBuild.toString()
    } else {
        "No Records Found"
    }
    Column() {
        Text(text = text)
    }
}

@SuppressLint("Range")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text by remember {
        mutableStateOf("")
    }
    var loadText by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val value = ContentValues()
    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it })
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                value.put(MyContentProvider.name, text)
                context.contentResolver.insert(MyContentProvider.CONTENT_URI, value)
            }) {
            Text(text = "Insert")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                var cursor = context.contentResolver.query(
                    MyContentProvider.CONTENT_URI,
                    null,
                    null,
                    null
                )
                loadText = if (cursor!!.moveToFirst()) {
                    val strBuild = StringBuilder()
                    while (!cursor.isAfterLast) {
                        strBuild.append(
                            """
                ${cursor.getString(cursor.getColumnIndex("id"))}-${
                                cursor.getString(
                                    cursor.getColumnIndex(
                                        "name"
                                    )
                                )
                            }
                """.trimIndent()
                        )
                        cursor.moveToNext()
                    }
                    strBuild.toString()
                } else {
                    "No Records Found"
                }
            }) {
            Text(text = "Load")
        }
        Text(text = loadText)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}