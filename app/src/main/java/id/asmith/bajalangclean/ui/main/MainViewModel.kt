package id.asmith.bajalangclean.ui.main

import android.arch.lifecycle.ViewModel
import id.asmith.bajalangclean.util.AppConstants.USER_CURRENT_CITY
import id.asmith.bajalangclean.util.PreferencesUtil


/**
 * Created by Agus Adhi Sumitro on 10/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class MainViewModel : ViewModel() {

    private var mPrefs: PreferencesUtil? = null
    private var mNavigator: MainNavigation? = null

    fun mainViewModel(navigation: MainNavigation,
                      preferences: PreferencesUtil) {

        this.mNavigator = navigation
        this.mPrefs = preferences

    }

    fun fragmentTransition(){ replaceWithMainPlace() }

    private fun replaceWithMainPlace() = mNavigator?.replaceWithMainPlace()

    fun replaceWithListPlace() = mNavigator?.replaceWithListPlace()

    fun replaceWithDetailPlace() = mNavigator?.replaceWithDetailPlace()

    fun getCurrentCity(): String{
        return mPrefs!!.getString(USER_CURRENT_CITY,
                "city")
    }

    var getPassingCategory: String? = null
    fun setPassingCategory(category: String) { this.getPassingCategory = category }

    var getPassingId: Int? = null
    fun setPassingId(id: Int) { this.getPassingId = id }

    fun clearPrefs(){
        mPrefs!!.clearPrefs()
        mNavigator!!.startedActivity()
    }

}