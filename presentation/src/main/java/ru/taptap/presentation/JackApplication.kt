package ru.taptap.presentation

import dagger.android.support.DaggerApplication
import ru.taptap.presentation.di.component.DaggerJackApplicationComponent

class JackApplication : DaggerApplication() {

    private val applicationInjector by lazy {
        DaggerJackApplicationComponent.builder()
                .application(this)
                .build()
    }

    override fun applicationInjector() = applicationInjector

}
//My guess is that you missed the AndroidSupportInjectionModule.
// Add it to the list of modules in your AppComponent.
// I can't really tell because you did not post your BaseActivity.