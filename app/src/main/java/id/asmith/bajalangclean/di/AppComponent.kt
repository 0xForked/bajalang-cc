package id.asmith.bajalangclean.di

import dagger.Component
import id.asmith.bajalangclean.di.module.AppModule
import id.asmith.bajalangclean.di.module.RemoteModule
import id.asmith.bajalangclean.ui.main.MainActivity
import id.asmith.bajalangclean.ui.main.fragment.detail.FragmentDetailPlace
import id.asmith.bajalangclean.ui.main.fragment.list.FragmentListPlace
import id.asmith.bajalangclean.ui.splash.SplashActivity
import id.asmith.bajalangclean.ui.started.GetStartedActivity
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

    fun inject(target: GetStartedActivity)

    fun inject(target: MainActivity)

    fun inject(target: FragmentListPlace)

    fun inject(target: FragmentDetailPlace)

}