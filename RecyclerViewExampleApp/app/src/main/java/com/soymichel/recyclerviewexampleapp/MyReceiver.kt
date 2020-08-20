package com.soymichel.recyclerviewexampleapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class MyReceiver : BroadcastReceiver() {

    private val NOTIFICATION_CHANELL_ID:String = "NOTIF_CHANNEL"
    private val NOTIFICATION_ID = 777

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.i(MainActivity.MI_TAG, "MyReceiver.onReceive")
        configurarNotificationChannel(context)
        lanzarNotificacion(context)

    }

    private fun configurarNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // The id of the notification channel.
            val mNotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // The user-visible name of the channel.
            val name = context.getString(R.string.abc_action_bar_home_description)
            // The user-visible description of the channel.
            val description = context.getString(R.string.abc_action_bar_home_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(NOTIFICATION_CHANELL_ID, name, importance)

            // Configure the notification channel.
            mChannel.description = description
            mChannel.enableLights(true)
            // Sets the notification light color for notifications posted to this
            // channel, if the device supports this feature.
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            mNotificationManager.createNotificationChannel(mChannel)
        }
    }

    private fun lanzarNotificacion(context: Context) {
        // https://developer.android.com/training/notify-user/navigation
        // Using NotificationCompat.Builder to add the notification objects
        val mBuilder = NotificationCompat.Builder(context, NOTIFICATION_CHANELL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Android ATC Notification")
            .setContentText("Check Android ATC New Course !!")

        // Creates an explicit intent for an Activity in your app
        val resultIntent = Intent(context, ResultActivity::class.java)

        val resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        mBuilder.setContentIntent(resultPendingIntent)
        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // mNotificationId is a unique integer your app uses to identify the
        // notification. For example, to cancel the notification,
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build())
    }
}
