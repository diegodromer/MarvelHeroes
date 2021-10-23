package com.diegolima.marvelheroes.core.network

import com.diegolima.marvelheroes.BuildConfig
import com.diegolima.marvelheroes.domain.network.MarvelCharacterService
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun providerHttpClient(authInterceptor: AuthInterceptor) : OkHttpClient{
    val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)

    if(BuildConfig.DEBUG){
        client.addNetworkInterceptor(StethoInterceptor())
    }

    client.addInterceptor(authInterceptor)
    return client.build()
}

fun providerRetrofit(client: OkHttpClient) : Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

fun providerMarvelApi(retrofit: Retrofit): MarvelCharacterService =
    retrofit.create(MarvelCharacterService::class.java)