package id.asmith.bajalangclean.ui.main.fragment.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import id.asmith.bajalangclean.BajalangApp
import id.asmith.bajalangclean.R
import id.asmith.bajalangclean.data.remote.ApiService
import id.asmith.bajalangclean.data.remote.model.Place
import id.asmith.bajalangclean.ui.main.MainViewModel
import id.asmith.bajalangclean.ui.main.fragment.list.rv.DataAdapter
import id.asmith.bajalangclean.ui.main.fragment.list.rv.DataListener
import id.asmith.bajalangclean.util.reactive.SchedulerProviderNavigation
import kotlinx.android.synthetic.main.fragment_main_list_place.*
import javax.inject.Inject

/**
 * Created by Agus Adhi Sumitro on 10/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class FragmentListPlace : Fragment(), DataListener, FragmentListNavigation {

    @Inject lateinit var mApiService : ApiService
    @Inject lateinit var mScheduler: SchedulerProviderNavigation

    private val mViewModelMain: MainViewModel by lazy {
        ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    private val mViewModelThis: FragmentListViewModel by lazy {
        ViewModelProviders.of(activity!!).get(FragmentListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_list_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        inject()

        val city = mViewModelMain.getCurrentCity()
        val category = mViewModelMain.getPassingCategory.toString()

        mViewModelThis.fragmentListViewModel(this, mApiService, mScheduler)

        mViewModelThis.getListPlace(city, category)

        initList()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater){
        super.onCreateOptionsMenu(menu, inflater)
        menu!!.findItem(R.id.action_find_location).isVisible = false
    }

    private fun initList(){
        rv_main_list_content.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager =
                LinearLayoutManager(activity)
        rv_main_list_content.layoutManager = layoutManager
    }

    override fun initAdapter() {
        rv_main_list_content.adapter =
                DataAdapter(mViewModelThis.placeList, this)
    }

    override fun onItemClick(place: Place) {
        mViewModelMain.setPassingId(place.id)
        mViewModelMain.replaceWithDetailPlace()
    }

    override fun hideErrorMessage() { lay_main_error.visibility = View.GONE }

    override fun hideList() { rv_main_list_content.visibility = View.GONE }

    override fun loadingHide() { image_main_list_loading.visibility = View.GONE }

    override fun loadingShow() { image_main_list_loading.visibility = View.VISIBLE }

    override fun showErrorMessage() { lay_main_error.visibility = View.VISIBLE  }

    override fun showList() { rv_main_list_content.visibility = View.VISIBLE  }

    private fun inject() { BajalangApp.mInstance.mAppComponent.inject(this) }

    override fun onDetach() {
        super.onDetach()
        mViewModelThis.placeList.clear()
    }

}