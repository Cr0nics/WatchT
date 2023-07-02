package com.example.watcht.core

import com.example.utils.utils.BASE_URL
import com.example.watcht.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object retrofitHelper {

    @Provides
    @Singleton
    fun BaseUrlProvider() = BASE_URL

    @Provides
    @Singleton
    fun ConnectionTimeOut() = 60L


    fun ProvideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideInterceptor() = if (BuildConfig.DEBUG) {

        val logginInterceptor = HttpLoggingInterceptor()
        logginInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        logginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        val requestInterceptor = Interceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter("api_key", "bfad11b3d8bcadf18965853619db810b")
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        OkHttpClient.Builder().addInterceptor(requestInterceptor).addInterceptor(logginInterceptor)
    } else {
        OkHttpClient.Builder().build()
    }


    fun retrofitHelper(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}