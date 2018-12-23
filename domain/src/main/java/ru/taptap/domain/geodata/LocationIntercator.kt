package ru.taptap.domain.geodata

import io.reactivex.Single

interface LocationIntercator {
    fun getLocation(): Single<String>
}