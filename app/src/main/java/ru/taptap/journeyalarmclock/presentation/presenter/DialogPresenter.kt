package ru.taptap.journeyalarmclock.presentation.presenter

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.taptap.journeyalarmclock.presentation.view.DialogView
import ru.taptap.journeyalarmclock.ui.MainActivity
import android.support.v4.app.ActivityCompat



@InjectViewState
class DialogPresenter : MvpPresenter<DialogView>() {
    val REQUEST_PERMISSION_RESULT_CODE = 42

    fun onShowDialogClick() {
        viewState.showDialog()
    }

    fun onHideDialog() {
        viewState.hideDialog()
    }

    fun checkLocationPermission(activity: Activity): Boolean {
        if (!hasLocationPermission(activity)) {
            Log.e("Tuts+", "Does not have location permission granted")
            requestLocationPermission(activity)
            return false
        }

        return true
    }

    private fun requestLocationPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSION_RESULT_CODE)
    }

    private fun hasLocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
}