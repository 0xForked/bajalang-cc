package id.asmith.bajalangclean.ui.splash

import android.arch.lifecycle.ViewModel
import id.asmith.bajalangclean.util.AppConstants.USER_LOG_STATUS
import id.asmith.bajalangclean.util.PreferencesUtil
import java.util.*


/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class SplashViewModel : ViewModel() {

    private var mNavigator: SplashNavigation? = null
    private var mPrefs: PreferencesUtil? = null

    fun setNavigation(navigator: SplashNavigation) { this.mNavigator = navigator }
    fun setPrefs(preferences: PreferencesUtil) { this.mPrefs = preferences }

    private fun isUserDataExist(): Boolean{
        return mPrefs!!.getBoolean(USER_LOG_STATUS,
                            false)
    }

    fun startTask() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                try {

                    changeActivity()

                } catch (e: Exception) {

                    e.printStackTrace()

                }
            }
        }, 2000)
    }

    private fun changeActivity() {
        if (isUserDataExist())
            mNavigator!!.openMainActivity()
        else
            mNavigator!!.openStartedActivity()
    }

}