package com.softwareit.sduhub.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.softwareit.sduhub.data.local.LocalDatabase
import com.softwareit.sduhub.data.network.backend.BackendService
import com.softwareit.sduhub.utils.Constants.Companion.APPLICATION_DATABASE
import com.softwareit.sduhub.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//db
fun provideNoteDao(appDatabase: LocalDatabase) = appDatabase.noteDao()
fun provideFaqDao(appDatabase: LocalDatabase) = appDatabase.faqDao()
fun provideAppDatabase(context: Context): LocalDatabase {
    return Room.databaseBuilder(
        context,
        LocalDatabase::class.java,
        APPLICATION_DATABASE
    ).build()
}

//network
fun provideRetrofit(
    okHttpClient: OkHttpClient,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun provideBackendService(retrofit: Retrofit): BackendService =
    retrofit.create(BackendService::class.java)

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