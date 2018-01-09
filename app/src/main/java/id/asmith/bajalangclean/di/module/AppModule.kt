package id.asmith.bajalangclean.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import id.asmith.bajalangclean.BajalangApp
import id.asmith.bajalangclean.util.PreferencesUtil
import id.asmith.bajalangclean.util.reactive.SchedulerProvider
import id.asmith.bajalangclean.util.reactive.SchedulerProviderNavigation
import javax.inject.Singleton




/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
@Module
class AppModule(private val bajalangApp: BajalangApp) {

    @Provides
    @Singleton
    fun provideApplication() = bajalangApp

    @Provides
    @Singleton
    fun providePreferencesUtil(sharedPreferences: SharedPreferences):
            PreferencesUtil = PreferencesUtil(sharedPreferences)

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = provideApplication()
            .getSharedPreferences("", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProviderNavigation = SchedulerProvider()


}