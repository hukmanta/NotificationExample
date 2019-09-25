package id.code.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var button : Button
    var channelId = "0001"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        // create notification channel
        createNotificationChannel()
        button = myButton
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            val intent2 = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            val onClickNotif = PendingIntent.getActivity(this, 0, intent2, 0)
            val onActionTapped = PendingIntent.getActivity(this,0,intent, 0)
            val notification = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Hello Human")
                .setContentIntent(onClickNotif)
                .setAutoCancel(true)
                .setContentText("Welcome, I'm Ndroid 18")
                .setCategory(Notification.CATEGORY_EVENT)
                .addAction(R.drawable.ic_launcher_foreground, "Search something on web", onActionTapped)
                .build()
            with(NotificationManagerCompat.from(this)){
                if (getNotificationChannel(channelId) == null){
                    createNotificationChannel()
                }
                notify(Math.random().toInt(), notification)

            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "My Notification Channel"
            val descriptionText = "This is my Default notification channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId,name, importance).apply {
                description = descriptionText
            }
            channel.enableVibration(true)
            
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
