package com.example.slide13.components

import android.media.AudioManager
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MediaPlayer() {
    val ctx = LocalContext.current
    val mediaPlayer = MediaPlayer()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(6.dp),
            text = "Play Audio from URL",
            fontSize = 20.sp
        )

        Button(
            modifier = Modifier
                .width(300.dp)
                .padding(7.dp),
            onClick = {
                val audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

                try {
                    mediaPlayer.setDataSource(audioUrl)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                Toast.makeText(ctx, "Audio started playing..", Toast.LENGTH_SHORT).show()
            }) {
            Text(text = "Play Audio")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .width(300.dp)
                .padding(7.dp),
            onClick = {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    Toast.makeText(ctx, "Audio has been  paused..", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(ctx, "Audio not played..", Toast.LENGTH_SHORT).show()
                }
            }) {
            Text(text = "Pause Audio")
        }
        Button(
            modifier = Modifier
                .width(300.dp)
                .padding(7.dp),
            onClick = {

                    mediaPlayer.start()
                    Toast.makeText(ctx, "Audio has been continued..", Toast.LENGTH_SHORT).show()

            }) {
            Text(text = "Continue Audio")
        }
    }
}
