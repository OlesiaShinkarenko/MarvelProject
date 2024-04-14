package effective.office.marvelproject.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import effective.office.marvelproject.network.either.EitherCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp


private const val BASE_URL =
    "https://gateway.marvel.com/v1/public/"

private val authInterceptor = Interceptor { chain ->
    val newUrl = chain.request().url
        .newBuilder()
        .addQueryParameter("apikey", Constant.API_KEY)
        .addQueryParameter("ts", Constant.ts)
        .addQueryParameter("hash", Constant.hash())
        .build()

    val newRequest = chain.request()
        .newBuilder()
        .url(newUrl)
        .build()

    chain.proceed(newRequest)
}

private val logInterceptor =
    HttpLoggingInterceptor { message -> Timber.tag("Okhttp").d(message) }.apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }


private val client = OkHttpClient().newBuilder()
    .addInterceptor(authInterceptor)
    .addNetworkInterceptor(logInterceptor)
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

class Constant {
    companion object {
        const val API_KEY = "0942deebe7c90aafc2a7ec87d85c34dd"
        const val PRIVATE_API_KEY = "0ddaddbaca3b9df2847c4b09977e9169af7cbd49"
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        fun hash(): String {
            val input = "$ts${PRIVATE_API_KEY}${API_KEY}"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}


val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(EitherCallAdapterFactory())
    .client(client)
    .baseUrl(BASE_URL)
    .build()