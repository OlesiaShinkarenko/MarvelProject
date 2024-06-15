package effective.office.marvelproject.notification.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import effective.office.marvelproject.notification.HeroNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PushService : FirebaseMessagingService() {

    @Inject
    lateinit var heroNotification: HeroNotification

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        CoroutineScope(Dispatchers.IO).launch {
            heroNotification.sendNotification()
        }
    }
}