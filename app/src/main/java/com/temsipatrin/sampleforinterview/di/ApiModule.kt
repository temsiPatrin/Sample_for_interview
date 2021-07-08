package com.temsipatrin.sampleforinterview.di

import com.temsipatrin.sampleforinterview.BuildConfig
import com.temsipatrin.sampleforinterview.remote.api.RickAndMortyApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    fun provideOkHttpClient(
        logInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

    fun provideRetrofit(client: OkHttpClient): RickAndMortyApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.API_SERVER_HOST)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RickAndMortyApiService::class.java)

    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }

}