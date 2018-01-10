package id.asmith.bajalangclean.ui.main

import android.arch.lifecycle.ViewModel
import android.util.Log
import id.asmith.bajalangclean.data.remote.ApiService
import id.asmith.bajalangclean.util.AppConstants.USER_CURRENT_CITY
import id.asmith.bajalangclean.util.PreferencesUtil
import id.asmith.bajalangclean.util.reactive.SchedulerProviderNavigation


/**
 * Created by Agus Adhi Sumitro on 10/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class MainViewModel : ViewModel() {

    private var mPrefs: PreferencesUtil? = null
    private var mNavigator: MainNavigation? = null
    private var mScheduler: SchedulerProviderNavigation? = null
    private var mApiService: ApiService? = null

    fun mainViewModel(navigation: MainNavigation,
                      preferences: PreferencesUtil,
                      scheduler: SchedulerProviderNavigation,
                      apiService: ApiService) {

        this.mNavigator = navigation
        this.mPrefs = preferences
        this.mScheduler = scheduler
        this.mApiService = apiService

    }

    fun fragmentTransition(){
        replaceWithMainPlace()
    }

    private fun replaceWithMainPlace() = mNavigator?.replaceWithMainPlace()

    private fun replaceWithListPlace() = mNavigator?.replaceWithListPlace()

    private fun replaceWithDetailPlace() = mNavigator?.replaceWithDetailPlace()

    fun getCurrentCity(): String{
        return mPrefs!!.getString(USER_CURRENT_CITY,
                "city")
    }

    fun getListPlace(city: String, category: String){

        replaceWithListPlace()

        mApiService!!.listPlace(city, category)
                .subscribeOn(mScheduler!!.multi())
                .observeOn(mScheduler!!.ui())
                .subscribe({ onSuccess ->
                    Log.d("Success", onSuccess.string())
                }, { onError ->
                    onError.printStackTrace()
                })
    }

    fun getDetailPlace(id: String){
        replaceWithDetailPlace()
    }
}