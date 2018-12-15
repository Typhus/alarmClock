package ru.taptap.journeyalarmclock.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import kotlinx.android.synthetic.main.activity_main.*
import ru.taptap.journeyalarmclock.R
import ru.taptap.journeyalarmclock.presentation.presenter.DialogPresenter
import ru.taptap.journeyalarmclock.presentation.view.DialogView


class MainActivity : MvpAppCompatActivity(), DialogView {

    //AIzaSyDx-rpupkrroCYLesg5--e9OHWL0d_msRY

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var dialogPresenter: DialogPresenter

    var alertDialog: AlertDialog? = null

//    @ProvidePresenterTag(presenterClass = DialogPresenter::class, type = PresenterType.GLOBAL)
//    fun provideDialogPresenterTag(): String = "Hello"
//
//    @ProvidePresenter(type = PresenterType.GLOBAL)
//    fun provideDialogPresenter() = DialogPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView.setOnClickListener { dialogPresenter.onShowDialogClick() }
        val checkLocationPermission = dialogPresenter.checkLocationPermission(this)
        if (checkLocationPermission) {
            startAwareness()
        }
    }

    val REQUEST_PERMISSION_RESULT_CODE = 42
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION_RESULT_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startAwareness()
                } else {
                    Log.e("Tuts+", "Location permission denied.")
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun startAwareness() {
//        val homeFence = LocationFence.entering(/*latitude*/ 39.92,/*longitude*/ -105.7, /*radius*/ 1000.0)
//
//        val intent = Intent(ACTION_FENCE)
//        val fencePendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//
//        mFenceBroadcastReceiver = FenceBroadcastReceiver()
//        registerReceiver(mFenceBroadcastReceiver, IntentFilter(ACTION_FENCE))
//
//        val builder = FenceUpdateRequest.Builder()
//        builder.addFence(KEY_SITTING_AT_HOME, sittingAtHomeFence, fencePendingIntent)
//
//        Awareness.FenceUpdateRequestenceApi.updateFences(mGoogleApiClient, builder.build())
    }

    override fun showDialog() {
        alertDialog = AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setOnDismissListener { dialogPresenter.onHideDialog() }
                .show()
    }

    override fun hideDialog() {
        alertDialog?.setOnDismissListener { }
        alertDialog?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()

        hideDialog()
    }
}
