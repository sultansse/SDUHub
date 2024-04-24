package com.softwareit.sduhub.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.softwareit.sduhub.data.local.room.LocalDatabase
import com.softwareit.sduhub.utils.Constants.Companion.APPLICATION_DATABASE
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//db
fun provideNoteDao(appDatabase: LocalDatabase) = appDatabase.noteDao()
fun provideAppDatabase(context: Context): LocalDatabase {
    return Room.databaseBuilder(
        context,
        LocalDatabase::class.java,
        APPLICATION_DATABASE
    ).build()
}

//network
fun provideRetrofit(
    moshi: Moshi,
    okHttpClient: OkHttpClient,
    baseUrl: String
): Retrofit {
    val factory = MoshiConverterFactory.create(moshi)
    val factoryNulls = factory.withNullSerialization()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(factoryNulls)
        .build()
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun provideHttpClient(context: Context): OkHttpClient {
    // Defining a cache of 5 MB size
    val cacheSize = (5 * 1024 * 1024).toLong()

//Initializing instance of Cache class
    val myCache = Cache(context.cacheDir, cacheSize)

//defining okhttpclient instance
    val okHttpClient = OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor(ChuckerInterceptor(context))
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(context))
                request
                    .newBuilder()
                    .cacheControl(
                        CacheControl.Builder()
                            .maxAge(30, TimeUnit.MINUTES)
                            .build()
                    )
                    .build()
            else
                request
                    .newBuilder()
                    .cacheControl(
                        CacheControl.Builder()
                            .maxStale(1, TimeUnit.DAYS)
                            .build()
                    )
                    .build()
            chain.proceed(request)
        }
        .build()

    return okHttpClient
}


inline fun <reified T> createService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

fun hasNetwork(context: Context): Boolean {
    val connectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
    }
}