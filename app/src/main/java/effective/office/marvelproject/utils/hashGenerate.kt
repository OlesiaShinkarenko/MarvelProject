package effective.office.marvelproject.utils

import effective.office.marvelproject.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

fun hash(): String {
    val input = "${ts}${BuildConfig.PRIVATE_API_KEY}${BuildConfig.API_KEY}"
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}

val ts = Timestamp(System.currentTimeMillis()).time.toString()