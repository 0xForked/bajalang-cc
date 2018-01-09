package id.asmith.bajalangclean.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.asmith.bajalangclean.BajalangApp
import id.asmith.bajalangclean.R
import id.asmith.bajalangclean.util.AppConstants.USER_CURRENT_CITY
import id.asmith.bajalangclean.util.AppConstants.USER_LAT
import id.asmith.bajalangclean.util.AppConstants.USER_LOG_IDENTITY
import id.asmith.bajalangclean.util.AppConstants.USER_LON
import id.asmith.bajalangclean.util.PreferencesUtil
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject lateinit var mPrefsUtil: PreferencesUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inject()

        val name =  mPrefsUtil.getString(USER_LOG_IDENTITY, "currentUserName")
        val city = mPrefsUtil.getString(USER_CURRENT_CITY, "currentUserCity")
        val latitude = mPrefsUtil.getString(USER_LAT, "currentUserCity")
        val longitude = mPrefsUtil.getString(USER_LON, "currentUserCity")

        thisIsUserName.text = name
        thisIsCity.text = city
        thisIsLatitude.text = latitude
        thisIsLongitude.text = longitude

    }

    private fun inject() {

        BajalangApp
                .mInstance
                .mAppComponent
                .inject(this)

    }

}
