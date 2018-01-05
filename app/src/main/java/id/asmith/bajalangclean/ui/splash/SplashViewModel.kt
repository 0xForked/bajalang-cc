package id.asmith.bajalangclean.ui.splash

import android.arch.lifecycle.ViewModel
import java.util.*


/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class SplashViewModel : ViewModel() {

    private var mNavigator: SplashNavigation? = null

    fun setNavigator(navigator: SplashNavigation) {
        this.mNavigator = navigator
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

        mNavigator!!.openMainActivity()

    }

}