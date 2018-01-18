package id.asmith.bajalangclean.ui.splash

import android.arch.lifecycle.ViewModel
import id.asmith.bajalangclean.util.AppConstants.USER_LOG_STATUS
import id.asmith.bajalangclean.util.PreferencesUtil
import id.asmith.bajalangclean.util.reactive.SchedulerProviderNavigation
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class SplashViewModel : ViewModel() {

    private var mNavigator: SplashNavigation? = null
    private var mPrefs: PreferencesUtil? = null
    private var mSchedulers: SchedulerProviderNavigation? = null

    fun splashViewModel(navigator: SplashNavigation,
                        preferences: PreferencesUtil,
                        schedulers: SchedulerProviderNavigation) {
        this.mNavigator = navigator
        this.mPrefs = preferences
        this.mSchedulers = schedulers
    }

    private fun isUserDataExist(): Boolean{
        return mPrefs!!.getBoolean(USER_LOG_STATUS,
                            false)
    }

    fun startTask() {

        Observable.interval(3, TimeUnit.SECONDS)
                .take(1)
                .observeOn(mSchedulers!!.ui())
                .observeOn(mSchedulers!!.multi())
                .subscribe { changeActivity() }

    }

    private fun changeActivity() {
        if (isUserDataExist())
            mNavigator!!.openMainActivity()
        else
            mNavigator!!.openStartedActivity()
    }

}