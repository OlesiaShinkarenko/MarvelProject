package effective.office.marvelproject.notification

import com.google.firebase.messaging.FirebaseMessagingService

class PushService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}