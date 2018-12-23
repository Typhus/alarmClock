package ru.taptap.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.taptap.presentation.geodata.LocationInteractor
import java.text.DecimalFormat
import javax.inject.Inject

class LocationViewModel @Inject constructor(
        private val locationInteractor: LocationInteractor
) : ViewModel() {

    private lateinit var users: MutableLiveData<String>
    private val disposables = CompositeDisposable()

    fun getCurrentLocation(): LiveData<String> {
        if (!::users.isInitialized) {
            users = MutableLiveData()
            loadCurrentLocation()
        }
        return users
    }

    private fun loadCurrentLocation() {
    }

    fun calcLengthInKm(startPosition: LatLng, endPosition: LatLng): Double {
        val lat1 = startPosition.latitude
        val lat2 = endPosition.latitude
        val lon1 = startPosition.longitude
        val lon2 = endPosition.longitude
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + (Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2))
        val c = 2 * Math.asin(Math.sqrt(a))
        val valueResult = RADIUS_OF_EARTH * c
        val km = valueResult / 1
        val newFormat = DecimalFormat("####")
        val kmInDec = Integer.valueOf(newFormat.format(km))
        val meter = valueResult % 1000
        val meterInDec = Integer.valueOf(newFormat.format(meter))
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec)

        return RADIUS_OF_EARTH * c
    }

    companion object {
        private const val RADIUS_OF_EARTH = 6371
    }
}