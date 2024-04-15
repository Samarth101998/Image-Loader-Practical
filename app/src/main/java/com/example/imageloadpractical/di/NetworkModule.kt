package com.example.imageloadpractical.di

import com.example.imageloadpractical.ApiService
import com.example.imageloadpractical.helper.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesApiService(): ApiService = Retrofit
        .Builder()
        .run {
            client(OkHttpClient.Builder().addInterceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        "Client-ID B2WIC_JXryB3bXuoTAanfw7ZxcoSU2nnis4SePrG7Pg"
                    )
                    .build()
                chain.proceed(newRequest)
            }.build())
            baseUrl(Const.BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            build()
        }.create(ApiService::class.java)

}