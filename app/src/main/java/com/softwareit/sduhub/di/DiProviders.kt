package com.softwareit.sduhub.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.softwareit.sduhub.data.local.room.LocalDatabase
import com.softwareit.sduhub.utils.Constants.Companion.APPLICATION_DATABASE
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    return OkHttpClient
        .Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .pingInterval(3, TimeUnit.SECONDS)
        .addInterceptor(ChuckerInterceptor(context))
        .build()
}


inline fun <reified T> createService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}