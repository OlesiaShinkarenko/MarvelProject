package effective.office.marvelproject.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {
    companion object {
        const val DB_NAME = "marvel_app"
        const val API_KEY = "0942deebe7c90aafc2a7ec87d85c34dd"
        const val BASE_URL =
            "https://gateway.marvel.com/v1/public/"
        const val PRIVATE_API_KEY = "0ddaddbaca3b9df2847c4b09977e9169af7cbd49"
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        fun hash(): String {
            val input = "$ts$PRIVATE_API_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        const val CHANNEL_ID = "id_heroes"
        const val CHANNEL_NAME = "hero_channel"
        const val MY_URI = "https://app.com"
        const val NOTIFICATION_ID = 1
        const val REQUEST_CODE = 1
    }
}