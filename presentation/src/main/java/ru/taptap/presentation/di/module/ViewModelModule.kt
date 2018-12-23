package ru.taptap.presentation.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.taptap.presentation.LocationViewModel
import ru.taptap.presentation.ViewModelFactory
import ru.taptap.presentation.di.key.ViewModelKey
import ru.taptap.presentation.di.scope.ApplicationScope

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LocationViewModel::class)
    abstract fun bindMapActivityViewModel(viewModel: LocationViewModel): ViewModel

    @Binds
    @ApplicationScope
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}