package effective.office.marvelproject.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import effective.office.marvelproject.MainActivity
import effective.office.marvelproject.R
import effective.office.marvelproject.data.local.MarvelAppDatabase
import effective.office.marvelproject.utils.Constants
import effective.office.marvelproject.utils.Constants.Companion.MY_URI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PushService : FirebaseMessagingService() {

    @Inject
    lateinit var marvelAppDatabase: MarvelAppDatabase

    @SuppressLint("MissingPermission")
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE
            else
                0

        CoroutineScope(Dispatchers.IO).launch {
            val randomId = marvelAppDatabase.characterDao().getRandomId()

            val clickIntent = Intent(
                Intent.ACTION_VIEW,
                "$MY_URI/index=${randomId}".toUri(),
                applicationContext,
                MainActivity::class.java
            )
            val clickPendingIntent: PendingIntent =
                TaskStackBuilder.create(applicationContext).run {
                    addNextIntentWithParentStack(clickIntent)
                    getPendingIntent(Constants.REQUEST_CODE, flag)
                }
            val notificationBuilder =
                NotificationCompat.Builder(applicationContext, Constants.CHANNEL_ID)
                    .setContentTitle(Constants.CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setAutoCancel(true)
                    .setContentText(randomId.toString())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                    .setContentIntent(clickPendingIntent)

            val notificationManager = NotificationManagerCompat.from(applicationContext)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    Constants.CHANNEL_ID,
                    Constants.CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(Constants.NOTIFICATION_ID, notificationBuilder.build())
        }
    }
}