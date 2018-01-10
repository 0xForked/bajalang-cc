package id.asmith.bajalangclean.util

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log


/**
 * Created by Agus Adhi Sumitro on 10/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class LocationListenerUtil : LocationListener {

    override fun onLocationChanged(location: Location)
    { Log.d("LocationListenerUtil" , "${location.latitude}, ${location.longitude}") }
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle)
    { Log.d("LocationListenerUtil","Status changed") }
    override fun onProviderEnabled(provider: String)
    { Log.d("LocationListenerUtil", "Provider enable") }
    override fun onProviderDisabled(provider: String)
    { Log.d("LocationListenerUtil", "Provider disable") }

}