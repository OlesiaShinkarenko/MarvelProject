package effective.office.marvelproject.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import effective.office.marvelproject.BuildConfig
import effective.office.marvelproject.network.either.EitherCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

private const val BASE_URL =
    "https://gateway.marvel.com/v1/public/"

private val authInterceptor = Interceptor { chain ->
    val newUrl = chain.request().url
        .newBuilder()
        .addQueryParameter("apikey", BuildConfig.API_KEY)
        .addQueryParameter("ts", Constant.ts)
        .addQueryParameter("hash", Constant.hash())
        .build()

    val newRequest = chain.request()
        .newBuilder()
        .url(newUrl)
        .build()

    chain.proceed(newRequest)
}


private val client = OkHttpClient().newBuilder()
    .addInterceptor(authInterceptor)
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

class Constant {
    companion object {
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        fun hash(): String {
            val input = "$ts${BuildConfig.PRIVATE_API_KEY}${BuildConfig.API_KEY}"
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