package br.com.hey.network

import android.content.res.Resources
import br.com.hey.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val API_TOKEN = Resources.getSystem().getString(R.string.API_TOKEN)
val API_URL = Resources.getSystem().getString(R.string.API_URL)

fun client() =
    OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS).addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(Interceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $API_TOKEN")
                .build()
            chain.proceed(newRequest)
        })
        .build()

fun gson() : Gson = GsonBuilder().create()

fun retrofit() : Retrofit =
    Retrofit.Builder()
        .baseUrl(API_URL)
        .client(client())
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .build()

fun apiService() : IApiService =
    retrofit().create(IApiService::class.java)