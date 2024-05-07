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
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

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
    // Create a trust manager that trusts all certificates
    val trustAllCertificates = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    })

    // Create SSL context that trusts all certificates
    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCertificates, SecureRandom())

    // Create OkHttpClient
    return OkHttpClient.Builder()
        .sslSocketFactory(sslContext.socketFactory, trustAllCertificates[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true } // Trust all hostnames
        .cache(Cache(context.cacheDir, (5 * 1024 * 1024).toLong())) // 5 MB cache
        .addInterceptor(ChuckerInterceptor(context)) // Add Chucker interceptor
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(context))
                request.newBuilder()
                    .cacheControl(
                        CacheControl.Builder()
                            .maxAge(30, TimeUnit.MINUTES)
                            .build()
                    )
                    .build()
            else
                request.newBuilder()
                    .cacheControl(
                        CacheControl.Builder()
                            .maxStale(1, TimeUnit.DAYS)
                            .build()
                    )
                    .build()
            chain.proceed(request)
        }
        .build()
}

fun provideCoilOkHttp(): OkHttpClient {
    val trustAllCertificates = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return emptyArray()
        }
    })

    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCertificates, SecureRandom())

    return OkHttpClient.Builder()
        .sslSocketFactory(sslContext.socketFactory, trustAllCertificates[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }
        .build()
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