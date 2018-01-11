package id.asmith.bajalangclean.ui.main.fragment.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import id.asmith.bajalangclean.data.remote.ApiService
import id.asmith.bajalangclean.util.reactive.SchedulerProviderNavigation
import org.json.JSONObject


/**
 * Created by Agus Adhi Sumitro on 11/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */


class FragmentDetailViewModel : ViewModel() {

    private var mNavigator: FragmentDetailNavigation? = null
    private var mApiService: ApiService? = null
    private var mScheduler: SchedulerProviderNavigation? = null

    private val mPlaceName = MutableLiveData<String>()
    private val mLatitude = MutableLiveData<Double>()
    private val mLongitude = MutableLiveData<Double>()

    fun fragmentDetailViewModel(navigation: FragmentDetailNavigation,
                              apiService: ApiService,
                              scheduler: SchedulerProviderNavigation) {

        this.mNavigator = navigation
        this.mApiService = apiService
        this.mScheduler = scheduler

    }

    fun getDetailPlace(placeId: Int){

        mApiService!!.detailPlace(placeId)
                .subscribeOn(mScheduler!!.multi())
                .observeOn(mScheduler!!.ui())
                .subscribe({ onSuccess ->

                    val mResult = JSONObject(onSuccess.string())
                    val mPlaceData = mResult.getJSONObject("detail")

                    mPlaceData.let {

                        val placeName = it.getString("name")
                        val placeLat = it.getString("lat")
                        val placeLon = it.getString("lon")

                        mPlaceName.value = placeName
                        mLatitude.value = placeLat.toDouble()
                        mLongitude.value = placeLon.toDouble()

                        mNavigator!!.showData()
                        mNavigator!!.hideLoading()

                    }

                }, { onError ->

                    onError.printStackTrace()
                    onError.let {
                        mNavigator!!.showErrorMessage()
                        mNavigator!!.hideLoading()
                    }

                })

    }

    fun getPlaceName(): LiveData<String> = mPlaceName
    fun getLatitude(): LiveData<Double> = mLatitude
    fun getLongitude(): LiveData<Double> = mLongitude

}