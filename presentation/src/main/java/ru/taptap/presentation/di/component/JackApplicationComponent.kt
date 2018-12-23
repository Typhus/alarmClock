package ru.taptap.presentation.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.taptap.presentation.JackApplication
import ru.taptap.presentation.di.module.ActivityModule
import ru.taptap.presentation.di.module.ApplicationModule
import ru.taptap.presentation.di.scope.ApplicationScope
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    ApplicationModule::class
])
@Singleton // TODO Как это работает?
@ApplicationScope
interface JackApplicationComponent: AndroidInjector<JackApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: JackApplication): Builder

        fun build() : JackApplicationComponent
    }

}