package com.diegolima.marvelheroes.core.network

import com.diegolima.marvelheroes.BuildConfig
import com.diegolima.marvelheroes.utils.getCurrentDate
import com.diegolima.marvelheroes.utils.md5
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*


class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        var url = request
            .url()
            .newBuilder()
            .addQueryParameter("ts", Date().getCurrentDate("yyyy-MM-dd'T'HH:mm'Z'"))
            .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_KEY)
            .addQueryParameter("hash", getStringToHash().md5())
            .build()

        request = request
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}

fun getStringToHash(): String =
    Date().getCurrentDate("yyyy-MM-dd'T'HH:mm'Z'") + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY