package com.factorial.testtask.di

import com.factorial.common.const.Const
import com.factorial.data.service.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val serviceDi = module {

    fun provideService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    fun provideRetrofit(okHttpClient: OkHttpClient, factory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(factory)
            .client(okHttpClient)
            .build()
    }

    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClientBuilder = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    fun provideConverterFactory(): Converter.Factory {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        return MoshiConverterFactory.create(moshi)
    }


    single { provideOkHttpClient() }
    single { provideConverterFactory() }

    single { provideRetrofit(okHttpClient = get(), factory = get()) }

    single { provideService(retrofit = get()) }

}