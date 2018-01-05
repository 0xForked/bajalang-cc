package id.asmith.bajalangclean

import android.app.Application
import id.asmith.bajalangclean.di.AppComponent
import id.asmith.bajalangclean.di.DaggerAppComponent
import id.asmith.bajalangclean.di.module.AppModule


/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class BajalangApp : Application() {

    companion object {
        lateinit var mInstance: BajalangApp
    }

    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mAppComponent = DaggerAppComponent
                                .builder()
                                .appModule(AppModule(this))
                                .build()

    }

}