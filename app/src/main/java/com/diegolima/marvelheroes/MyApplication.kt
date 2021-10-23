package com.diegolima.marvelheroes

import android.app.Application
import com.diegolima.marvelheroes.core.networkApiModule
import com.diegolima.marvelheroes.core.networkServiceModule
import com.diegolima.marvelheroes.core.repositoryModule
import com.diegolima.marvelheroes.core.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin()

        if (BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this@MyApplication)
        }
    }

    private fun startKoin(){
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkApiModule,
                    networkServiceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}