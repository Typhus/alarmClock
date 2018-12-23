package ru.taptap.domain.geodata

import io.reactivex.Maybe

interface GeoDataRepository {

    fun getGeoData(): Maybe<String>

}