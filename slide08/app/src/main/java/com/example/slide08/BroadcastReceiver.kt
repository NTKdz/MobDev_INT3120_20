package com.example.slide08

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BroadcastReceiver : BroadcastReceiver() {
    private val TAG = "MyBroadcastReceiver"
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "received")
        Toast.makeText(context, "Action: " + intent?.action, Toast.LENGTH_SHORT).show();
    }
}