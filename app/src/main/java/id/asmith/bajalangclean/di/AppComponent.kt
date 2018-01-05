package id.asmith.bajalangclean.di

import dagger.Component
import id.asmith.bajalangclean.di.module.AppModule
import id.asmith.bajalangclean.di.module.RemoteModule
import id.asmith.bajalangclean.ui.splash.SplashActivity
import javax.inject.Singleton




/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
@Singleton
@Component(modules = [
    (AppModule::class),
    (RemoteModule::class)
])

interface AppComponent {

    fun inject(target: SplashActivity)

}