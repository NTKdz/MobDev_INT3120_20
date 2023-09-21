package com.example.slide07.components

import android.app.Activity
import android.app.ListActivity
import android.content.Intent
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
fun SendMessageLayout() {
    var message by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf("") }
    val sendMessageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Handle the result if needed
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data!!.getStringExtra("feedback")
                feedback = data ?: "nothing"
            }
        }

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Send a Message",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        BasicTextField(
            value = message,
            onValueChange = {
                message = it
            },
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        val context = LocalContext.current

        Button(
            onClick = {
                // Create an implicit intent to send a message
                sendMessageLauncher.launch(
                    Intent(
                        context,
                        GreetingActivity::class.java
                    ).putExtra("value", message)
                )
                // Start the second activity
            },
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Text(text = "Send")
        }

        Text(text = "feedBack: $feedback")
    }
}


@Preview
@Composable
fun SendMessageAndReceiveMessage() {
    val messageReceived = remember { mutableStateOf(false) }

}
