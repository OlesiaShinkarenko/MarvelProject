package effective.office.marvelproject.notification

import android.Manifest
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import effective.office.marvelproject.MainActivity
import effective.office.marvelproject.R
import effective.office.marvelproject.data.local.MarvelAppDatabase
import effective.office.marvelproject.utils.Constants

class HeroNotification(
    private val applicationContext: Context,
    private val marvelAppDatabase: MarvelAppDatabase,
    private val notificationManager: NotificationManagerCompat
) {
    fun sendNotification(){
        val randomId = marvelAppDatabase.characterDao().getRandomId()

        val clickIntent = createIntent(
            applicationContext,
            "${Constants.MY_URI}/index=${randomId}".toUri()
        )

        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE
            else
                0
        val clickPendingIntent: PendingIntent =
            createPendingIntent(applicationContext, clickIntent, flag, Constants.REQUEST_CODE)

        val notificationBuilder = notificationBuilder(
            applicationContext,
            randomId.toString(),
            Constants.CHANNEL_ID,
            clickPendingIntent
        )

        if (checkPermission(applicationContext)) {
            notificationManager.notify(
                Constants.NOTIFICATION_ID,
                notificationBuilder.build()
            )
        }
    }
    private fun checkPermission(context: Context): Boolean {
        return Build.VERSION.SDK_INT < 33 || (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun createIntent(context: Context, uri: Uri): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            uri,
            context,
            MainActivity::class.java
        )
    }

    private fun createPendingIntent(
        context: Context,
        clickIntent: Intent,
        flag: Int,
        requestCode: Int
    ) =
        TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(requestCode, flag)
        }

    private fun notificationBuilder(
        context: Context,
        content: String,
        channelId: String,
        intent: PendingIntent
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channelId)
            .setContentTitle(channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            .setContentIntent(intent)
    }
}