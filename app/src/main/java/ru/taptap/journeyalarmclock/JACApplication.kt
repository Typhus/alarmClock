package ru.taptap.journeyalarmclock

import android.app.Application

class JACApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: JACApplication
    }

}
