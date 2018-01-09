package id.asmith.bajalangclean.ui.started

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.graphics.Paint
import android.graphics.Typeface
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import id.asmith.bajalangclean.BajalangApp
import id.asmith.bajalangclean.R
import id.asmith.bajalangclean.ui.main.MainActivity
import id.asmith.bajalangclean.util.PreferencesUtil
import kotlinx.android.synthetic.main.activity_get_started.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class GetStartedActivity : AppCompatActivity(), StartedNavigation {

    private var locationManager: LocationManager? = null

    @Inject lateinit var mPrefsUtil: PreferencesUtil

    private val mViewModel: StartedViewModel by lazy {
        ViewModelProviders.of(this).get(StartedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        inject()

        mViewModel.setContext(this)
        mViewModel.setNavigation(this)
        mViewModel.setPrefs(mPrefsUtil)

        mViewModel.getCity().observe(this, Observer {
            text_started_location.text = it.toString()
        })

        mViewModel.getLatitude().observe(this, Observer {
            save_latitude.text = it.toString()
        })

        mViewModel.getLongitude().observe(this, Observer {
            save_longitude.text = it.toString()
        })

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return
        }

        //want to use LocationManager.NETWORK_PROVIDER or
        //LocationManager.GPS_PROVIDER its ok, up to you!
        locationManager!!.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                90000,
                10F,
                mViewModel.locationListener)

        text_started_next.apply {
            paintFlags = (text_started_next.paintFlags or Paint.UNDERLINE_TEXT_FLAG)
            setTypeface(null, Typeface.BOLD)
            setOnClickListener {
                val name = input_started_name.text.toString()
                val city = text_started_location.text.toString()
                val latitude = save_latitude.text.toString()
                val longitude = save_longitude.text.toString()
                if (name.isEmpty()) input_started_name.error = getString(R.string.empty_name)
                if (!name.isEmpty()) mViewModel.savePrefs(name, latitude, longitude, city)
            }
        }
    }

    override fun viewItemShow() {
        text_started_current.visibility = View.VISIBLE
        text_started_location.visibility = View.VISIBLE
        input_started_name.visibility = View.VISIBLE
        text_started_next.visibility = View.VISIBLE
    }

    override fun viewItemHide() {
        text_started_current.visibility = View.GONE
        text_started_location.visibility = View.GONE
        input_started_name.visibility = View.GONE
        text_started_next.visibility = View.GONE
    }

    override fun loadingDismiss(){
        image_started_loading.visibility = View.GONE
    }

    override fun loadingShow(){
        image_started_loading.visibility = View.VISIBLE
    }

    override fun startMainActivity(){
        startActivity<MainActivity>()
        finish()
    }

    private fun inject(){
        BajalangApp
                .mInstance
                .mAppComponent
                .inject(this)
    }

}
