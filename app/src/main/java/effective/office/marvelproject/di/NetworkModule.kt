package effective.office.marvelproject.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import effective.office.marvelproject.BuildConfig
import effective.office.marvelproject.data.network.EitherCallAdapterFactory
import effective.office.marvelproject.data.remote.services.MarvelApiService
import effective.office.marvelproject.utils.Constants
import effective.office.marvelproject.utils.hash
import effective.office.marvelproject.utils.ts
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("apikey", BuildConfig.API_KEY)
            .addQueryParameter("ts", ts)
            .addQueryParameter("hash", hash())
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(EitherCallAdapterFactory())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideMarvelApiService(retrofit: Retrofit): MarvelApiService =
        retrofit.create(MarvelApiService::class.java)
}