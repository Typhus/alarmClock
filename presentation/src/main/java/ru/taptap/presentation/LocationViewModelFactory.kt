package ru.taptap.presentation

import ru.taptap.presentation.geodata.LocationInteractor
import javax.inject.Inject

class LocationViewModelFactory @Inject constructor(
        private val interactor: LocationInteractor
) {
}