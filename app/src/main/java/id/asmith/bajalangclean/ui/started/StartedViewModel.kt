package id.asmith.bajalangclean.ui.started

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.support.v4.app.ActivityCompat
import android.util.Log
import id.asmith.bajalangclean.util.AppConstants.DEVICE_LAT
import id.asmith.bajalangclean.util.AppConstants.DEVICE_LON
import id.asmith.bajalangclean.util.AppConstants.USER_CURRENT_CITY
import id.asmith.bajalangclean.util.AppConstants.USER_LOG_IDENTITY
import id.asmith.bajalangclean.util.AppConstants.USER_LOG_STATUS
import id.asmith.bajalangclean.util.LocationListenerUtil
import id.asmith.bajalangclean.util.PreferencesUtil
import org.jetbrains.anko.toast
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
    private val mCountry = MutableLiveData<String>()
    private val mState = MutableLiveData<String>()
    private val mCity = MutableLiveData<String>()
    private val mDistrict = MutableLiveData<String>()
    private val mUrbanVillage = MutableLiveData<String>()
    private val mLatitude = MutableLiveData<Double>()
    private val mLongitude = MutableLiveData<Double>()

    fun startedViewModel(context: Context,
                         navigation: StartedNavigation,
                         preferences: PreferencesUtil) {

        this.mContext = context
        this.mNavigator = navigation
        this.mPrefs = preferences

    }

    fun savePrefs(identity: String,
                  latitude: String,
                  longitude: String,
                  city: String){

        mPrefs!!.putBoolean(USER_LOG_STATUS, true)
        mPrefs!!.putString(USER_LOG_IDENTITY, identity)
        mPrefs!!.putString(DEVICE_LAT, latitude)
        mPrefs!!.putString(DEVICE_LON, longitude)
        mPrefs!!.putString(USER_CURRENT_CITY, city)
        mNavigator!!.startMainActivity()

    }

    fun getLocationProvider(locationManager: LocationManager){
        try {

            if (ActivityCompat.checkSelfPermission(mContext!!,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(mContext!!,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                return
            }

            val isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER)

            val isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!isGPSEnabled or !isNetworkEnabled) {
                mContext!!.toast("Network or GPS provider is disabled")
            } else {

                if (isGPSEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            1800000, //30 min = 1.8 million ms
                            50F,
                            LocationListenerUtil())
                    Log.d("GPS", "GPS Provider enable")

                    getLocationData(locationManager)

                }

                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            1800000, //30 min = 1.8 million ms
                            50F,
                            LocationListenerUtil())
                    Log.d("Network", "Network Provider enable")

                    getLocationData(locationManager)
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getLocationData(locationManager: LocationManager){
        try {

            if (ActivityCompat.checkSelfPermission(mContext!!,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(mContext!!,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                return
            }

            locationManager.let {
                val location = it.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                location.let {
                    val latitude = it.latitude
                    val longitude = it.longitude
                    val geo = Geocoder(mContext, Locale.getDefault())
                    val addresses = geo.getFromLocation(latitude, longitude, 1)
                    val country = addresses[0].countryName
                    val state = addresses[0].adminArea
                    val city = addresses[0].subAdminArea
                    val districts = addresses[0].locality
                    val urbanVillage = addresses[0].subLocality

                    mNavigator!!.loadingDismiss()
                    mNavigator!!.viewItemShow()

                    mCountry.value = country
                    mState.value = state
                    mCity.value = city
                    mDistrict.value = districts
                    mUrbanVillage.value = urbanVillage
                    mLatitude.value = latitude
                    mLongitude.value = longitude
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getCurrentCountry(): LiveData<String> = mCountry
    fun getCurrentState(): LiveData<String> = mState
    fun getCurrentCity(): LiveData<String> = mCity
    fun getCurrentDistrict(): LiveData<String> = mDistrict
    fun getCurrentUrbanVillage(): LiveData<String> = mUrbanVillage
    fun getCurrentLatitude(): LiveData<Double> = mLatitude
    fun getCurrentLongitude(): LiveData<Double> = mLongitude

}