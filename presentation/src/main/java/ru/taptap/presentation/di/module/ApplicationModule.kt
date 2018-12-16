package ru.taptap.presentation.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import ru.taptap.presentation.JackApplication
import ru.taptap.presentation.di.scope.ApplicationScope

@Module(includes = [
    LocationModule::class
])
abstract class ApplicationModule {

    @Binds //TODO Что это и зачем?
    @ApplicationScope
    abstract fun bindApplicationContext(applicationContext: JackApplication): Context

}