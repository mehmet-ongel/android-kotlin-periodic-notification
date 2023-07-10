package com.techmania.periodicnotification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationReceiver : BroadcastReceiver() {

    val CHANNEL_ID = "1"
    val NOTIFICATION_ID = 1

    override fun onReceive(context: Context?, intent: Intent?) {

        createNotification(context)

    }

    //create a notification
    @SuppressLint("MissingPermission") //since we requested the permission in MainActivity, we can ignore the warning in this class
    fun createNotification(context: Context?) {

        context?.let {

            val builder = NotificationCompat.Builder(it, CHANNEL_ID)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val channel = NotificationChannel(
                    CHANNEL_ID, "Periodic Notification",
                    NotificationManager.IMPORTANCE_HIGH
                )
                val manager = it.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(channel)

            }

            builder.apply {
                setSmallIcon(R.drawable.small_icon)
                setContentTitle("Notification Title")
                setContentText("Notification Message")
                priority = NotificationCompat.PRIORITY_HIGH
            }

            val notificationManagerCompat = NotificationManagerCompat.from(it)
            notificationManagerCompat.notify(NOTIFICATION_ID, builder.build())
        }


    }
}