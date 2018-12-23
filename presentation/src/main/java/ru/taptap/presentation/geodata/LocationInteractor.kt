package ru.taptap.presentation.geodata

import io.reactivex.Maybe

interface LocationInteractor {

    fun getLocation(): Maybe<String>

}