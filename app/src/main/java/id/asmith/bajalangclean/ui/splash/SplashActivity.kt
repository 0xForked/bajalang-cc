package id.asmith.bajalangclean.ui.splash

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.asmith.bajalangclean.BajalangApp
import id.asmith.bajalangclean.R
import id.asmith.bajalangclean.ui.main.MainActivity
import id.asmith.bajalangclean.ui.started.GetStartedActivity
import id.asmith.bajalangclean.util.PreferencesUtil
import id.asmith.bajalangclean.util.reactive.SchedulerProviderNavigation
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashNavigation {

    @Inject lateinit var mPrefsUtil: PreferencesUtil
    @Inject lateinit var mScheduler: SchedulerProviderNavigation

    private val mViewModel: SplashViewModel by lazy {
        ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        inject()

        mViewModel.splashViewModel(this, mPrefsUtil, mScheduler)
        mViewModel.startTask()

    }

    override fun openStartedActivity(){
        startActivity<GetStartedActivity>()
        finish()
    }

    override fun openMainActivity() {
        startActivity<MainActivity>()
        finish()
    }

    private fun inject() {
        BajalangApp
                .mInstance
                .mAppComponent
                .inject(this)
    }

}
