package id.asmith.bajalangclean.ui.started

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import id.asmith.bajalangclean.util.AppConstants.USER_CURRENT_CITY
import id.asmith.bajalangclean.util.AppConstants.USER_LAT
import id.asmith.bajalangclean.util.AppConstants.USER_LOG_IDENTITY
import id.asmith.bajalangclean.util.AppConstants.USER_LOG_STATUS
import id.asmith.bajalangclean.util.AppConstants.USER_LON
import id.asmith.bajalangclean.util.PreferencesUtil
import java.util.*


/**
 * Created by Agus Adhi Sumitro on 09/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class StartedViewModel : ViewModel(){

    @SuppressLint("StaticFieldLeak")
    private var mContext: Context? = null
    private var mNavigator: StartedNavigation? = null
    private var mPrefs: PreferencesUtil? = null
    private val mCity = MutableLiveData<String>()
    private val mLatitude = MutableLiveData<Double>()
    private val mLongitude = MutableLiveData<Double>()

    fun setContext(context: Context) { mContext = context }
    fun setNavigation(navigation: StartedNavigation){ this.mNavigator = navigation }
    fun setPrefs(preferences: PreferencesUtil) { this.mPrefs = preferences }

    fun savePrefs(identity: String, latitude: String, longitude: String, city: String){
        mPrefs!!.putBoolean(USER_LOG_STATUS, true)
        mPrefs!!.putString(USER_LOG_IDENTITY, identity)
        mPrefs!!.putString(USER_LAT, latitude)
        mPrefs!!.putString(USER_LON, longitude)
        mPrefs!!.putString(USER_CURRENT_CITY, city)
        mNavigator!!.startMainActivity()
    }

    //define the listener
    val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {

            val geo = Geocoder(mContext, Locale.getDefault())
            val addresses = geo.getFromLocation(location.latitude, location.longitude, 1)
            val city = addresses[0].locality

            city.let {
                mNavigator!!.loadingDismiss()
                mNavigator!!.viewItemShow()
                mCity.value = it
                mLatitude.value = location.latitude
                mLongitude.value = location.longitude
            }

            Log.v("Status" , "${location.latitude}, ${location.longitude}, $city")
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    fun getCity(): LiveData<String> = mCity
    fun getLatitude(): LiveData<Double> = mLatitude
    fun getLongitude(): LiveData<Double> = mLongitude

}