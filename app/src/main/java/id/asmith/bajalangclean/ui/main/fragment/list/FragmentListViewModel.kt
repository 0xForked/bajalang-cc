package id.asmith.bajalangclean.ui.main.fragment.list

import android.arch.lifecycle.ViewModel
import id.asmith.bajalangclean.data.model.Place
import id.asmith.bajalangclean.data.remote.ApiService
import id.asmith.bajalangclean.util.reactive.SchedulerProviderNavigation


/**
 * Created by Agus Adhi Sumitro on 11/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class FragmentListViewModel : ViewModel(){

    private var mNavigator: FragmentListNavigation? = null
    private var mApiService: ApiService? = null
    private var mScheduler: SchedulerProviderNavigation? = null

    var placeList = ArrayList<Place>()

    fun fragmentListViewModel(navigation: FragmentListNavigation,
                              apiService: ApiService,
                              scheduler: SchedulerProviderNavigation) {

        this.mNavigator = navigation
        this.mApiService = apiService
        this.mScheduler = scheduler

    }

    fun getListPlace(city: String, category: String){

        mApiService!!.listPlace(city, category)
                .subscribeOn(mScheduler!!.multi())
                .observeOn(mScheduler!!.ui())
                .subscribe({ onSuccess ->

                    placeList = onSuccess as ArrayList<Place>
                    mNavigator!!.initAdapter().let {
                        mNavigator!!.loadingHide()
                        mNavigator!!.showList()
                    }

                }, { onError ->

                    onError.printStackTrace()
                    onError.let {
                        mNavigator!!.showErrorMessage()
                        mNavigator!!.loadingHide()
                    }

                })

    }

}