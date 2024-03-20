package com.softwareit.sduhub.di

import android.content.Context
import androidx.room.Room
import com.softwareit.sduhub.data.local.notes.NotesDatabase
import com.softwareit.sduhub.data.network.backend.BackendService
import com.softwareit.sduhub.utils.Constants
import com.softwareit.sduhub.utils.Constants.Companion.NOTE_TABLE
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
fun provideBackendService(): BackendService {

    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
//        .client(client)
        .build()
        .create(BackendService::class.java)
}

//fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
//    .addInterceptor(ChuckerInterceptor.Builder(androidContext()).build())
//    .build()