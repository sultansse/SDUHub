package com.softwareit.sduhub.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.softwareit.sduhub.data.local.notes.NotesDatabase
import com.softwareit.sduhub.data.network.backend.BackendService
import com.softwareit.sduhub.utils.Constants.Companion.BASE_URL
import com.softwareit.sduhub.utils.Constants.Companion.NOTE_TABLE
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//db
fun provideNoteDao(appDatabase: NotesDatabase) = appDatabase.noteDao()
fun provideAppDatabase(context: Context): NotesDatabase {
    return Room.databaseBuilder(
        context,
        NotesDatabase::class.java,
        NOTE_TABLE
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

fun provideService(retrofit: Retrofit): BackendService =
    retrofit.create(BackendService::class.java)

fun provideHttpClient(context: Context): OkHttpClient {
    return OkHttpClient
        .Builder()
        .addInterceptor(ChuckerInterceptor(context))
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}