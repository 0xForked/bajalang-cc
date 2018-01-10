package id.asmith.bajalangclean.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import id.asmith.bajalangclean.BajalangApp
import id.asmith.bajalangclean.R
import id.asmith.bajalangclean.data.remote.ApiService
import id.asmith.bajalangclean.ui.main.fragment.FragmentDetailPlace
import id.asmith.bajalangclean.ui.main.fragment.FragmentListPlace
import id.asmith.bajalangclean.ui.main.fragment.FragmentMainPlace
import id.asmith.bajalangclean.ui.started.GetStartedActivity
import id.asmith.bajalangclean.util.PreferencesUtil
import id.asmith.bajalangclean.util.reactive.SchedulerProviderNavigation
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainNavigation{

    @Inject lateinit var mPrefsUtil: PreferencesUtil
    @Inject lateinit var mScheduler: SchedulerProviderNavigation
    @Inject lateinit var mApiService : ApiService

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        inject()

        savedInstanceState.let {

            mViewModel.mainViewModel(
                    this,
                    mPrefsUtil,
                    mScheduler,
                    mApiService)

            mViewModel.fragmentTransition()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {

            R.id.action_find_location -> toast("Find me")
            R.id.action_search -> toast("Search data")
            R.id.action_settings -> toast("Settings")
        }

        return true
    }

    override fun replaceWithMainPlace() = replaceFragment(FragmentMainPlace())

    override fun replaceWithListPlace() = replaceFragment(FragmentListPlace())

    override fun replaceWithDetailPlace() = replaceFragment(FragmentDetailPlace())

    override fun startedActivity(){
        startActivity<GetStartedActivity>()
        finish()
    }

    private fun replaceFragment (fragment: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) clearBackStack()
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.replace(R.id.fragment_container, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    private fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    private fun inject(){
        BajalangApp
                .mInstance
                .mAppComponent
                .inject(this)
    }
}
