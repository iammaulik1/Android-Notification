package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import androidx.annotation.ContentView
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val desciption ="Test Notification"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        button.setOnClickListener {

            val intent = Intent(this , ActivityAfterNotification::class.java)

            val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            val contentView = RemoteViews(packageName , R.layout.activity_after_notification)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)

            {
                notificationChannel = NotificationChannel(channelId , desciption , NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this,channelId)
                .setContent(contentView)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.baseline_notifications_24))
                .setContentIntent(pendingIntent)
        }
            else {
            builder = Notification.Builder(this)
                .setContent(contentView)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.baseline_notifications_24))
                .setContentIntent(pendingIntent)
        }
            notificationManager.notify(1234,builder.build())

        }

    }
}