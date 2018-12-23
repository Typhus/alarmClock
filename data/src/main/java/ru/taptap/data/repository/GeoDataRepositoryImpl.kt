package ru.taptap.data.repository

import android.content.Context
import io.reactivex.Maybe
import ru.taptap.domain.geodata.GeoDataRepository
import javax.inject.Inject

class GeoDataRepositoryImpl @Inject constructor(
        private val context: Context
): GeoDataRepository {

    override fun getGeoData(): Maybe<String> {
        return Maybe.fromCallable {
            "123"
        }
    }
}
