package ru.taptap.presentation

import android.app.Application
import dagger.android.support.DaggerApplication

//class JackApplication: DaggerApplication() {
class JackApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    private val applicationInjector by lazy {
        //        DaggerJackApplicationComponent.builder()
//                .build()
    }

//    override fun applicationInjector() = applicationInjector

}
