package ru.taptap.presentation.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.taptap.presentation.MapsActivity
import ru.taptap.presentation.di.scope.ActivityScope

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMapsActivity(): MapsActivity
}
