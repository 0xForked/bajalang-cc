package id.asmith.bajalangclean.ui.started

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Paint
import android.graphics.Typeface
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import id.asmith.bajalangclean.BajalangApp
import id.asmith.bajalangclean.R
import id.asmith.bajalangclean.ui.main.MainActivity
import id.asmith.bajalangclean.util.PreferencesUtil
import kotlinx.android.synthetic.main.activity_started.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton
import javax.inject.Inject

class GetStartedActivity : AppCompatActivity(), StartedNavigation {

    private var mLocationManager: LocationManager? = null

    @Inject lateinit var mPrefsUtil: PreferencesUtil

    private val mViewModel: StartedViewModel by lazy {
        ViewModelProviders
                .of(this)
                .get(StartedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_started)

        inject()

        mViewModel.startedViewModel(this, this, mPrefsUtil)

        mLocationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager

        mViewModel.getLocationProvider(mLocationManager!!)

        viewModelObserver()

        clickListener()

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

    private fun clickListener(){
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

        text_started_location.apply {
            paintFlags = (text_started_location.paintFlags or Paint.UNDERLINE_TEXT_FLAG)
            setTypeface(null, Typeface.BOLD)
            setOnClickListener {
                alert {
                    title = "Location detail"
                    message =   """
                            Country : ${save_county.text}
                            State : ${save_state.text}
                            City : ${text_started_location.text}
                            District : ${save_district.text}
                            Urban Village : ${save_urban_village.text}
                            lat lon : ${save_latitude.text}, ${save_longitude.text}
                        """.trimIndent()
                    yesButton {  }
                }.show()
            }
        }
    }

    private fun viewModelObserver(){
        mViewModel.getCurrentCity().observe(this, Observer {
            text_started_location.text = it.toString()
        })
        mViewModel.getCurrentCountry().observe(this, Observer {
            save_county.text = it.toString()
        })
        mViewModel.getCurrentState().observe(this, Observer {
            save_state.text = it.toString()
        })
        mViewModel.getCurrentDistrict().observe(this, Observer {
            save_district.text = it.toString()
        })
        mViewModel.getCurrentUrbanVillage().observe(this, Observer {
            save_urban_village.text = it.toString()
        })
        mViewModel.getCurrentLatitude().observe(this, Observer {
            save_latitude.text = it.toString()
        })
        mViewModel.getCurrentLongitude().observe(this, Observer {
            save_longitude.text = it.toString()
        })
    }

    private fun inject(){
        BajalangApp
                .mInstance
                .mAppComponent
                .inject(this)
    }

}
