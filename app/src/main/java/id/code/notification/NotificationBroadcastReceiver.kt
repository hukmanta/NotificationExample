package id.code.notification

import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast

class NotificationBroadcastReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        when(action){
            Intent.ACTION_SCREEN_OFF -> Toast.makeText(context, "Screen Turn Off", Toast.LENGTH_LONG).show()
        }
    }

    object NotificationController {
        fun pendingIntent(context: Context, name: String, extras: Bundle?): PendingIntent {
            val intent = Intent(context, NotificationBroadcastReceiver::class.java).apply {
                action = name
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    putExtra(EXTRA_NOTIFICATION_ID, extras)
                }
            }

            return PendingIntent.getBroadcast(context, 0, intent, 0)
        }
    }
    
}
