package id.asmith.bajalangclean.ui.main.fragment.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import id.asmith.bajalangclean.BajalangApp
import id.asmith.bajalangclean.R
import id.asmith.bajalangclean.data.remote.ApiService
import id.asmith.bajalangclean.ui.main.MainViewModel
import id.asmith.bajalangclean.util.reactive.SchedulerProviderNavigation
import kotlinx.android.synthetic.main.fragment_main_detail_place.*
import javax.inject.Inject







/**
 * Created by Agus Adhi Sumitro on 10/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class FragmentDetailPlace : Fragment(), FragmentDetailNavigation, OnMapReadyCallback {

    @Inject lateinit var mApiService : ApiService
    @Inject lateinit var mScheduler: SchedulerProviderNavigation

    private lateinit var mMap: GoogleMap
    private var mapFragment: SupportMapFragment? = null

    private val mViewModelMain: MainViewModel by lazy {
        ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    private val mViewModelThis: FragmentDetailViewModel by lazy {
        ViewModelProviders.of(activity!!).get(FragmentDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_detail_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        inject()

        mViewModelThis.fragmentDetailViewModel(this, mApiService, mScheduler)
        mViewModelThis.getDetailPlace(mViewModelMain.getPassingId!!)
        viewModelObserver()

        mapFragment = SupportMapFragment.newInstance()
        mapFragment?.getMapAsync(this)
        childFragmentManager
                .beginTransaction()
                .replace(R.id.map, mapFragment)
                .commit()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val latitude = textLat.text.toString()
        val longitude = textLon.text.toString()
        val name = textName.text.toString()

        val content = LatLng(latitude.toDouble(), longitude.toDouble())
        mMap.apply {
            addMarker(MarkerOptions().position(content)
                    .title(name)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)))
                    .showInfoWindow()
            moveCamera(CameraUpdateFactory.newLatLng(content))
            mapType = GoogleMap.MAP_TYPE_NORMAL
            moveCamera(CameraUpdateFactory.newLatLngZoom(content, 15f))
            setMinZoomPreference(10F)
            uiSettings.isMyLocationButtonEnabled = true
            uiSettings.isZoomGesturesEnabled = true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.action_find_location).isVisible = false
        menu.findItem(R.id.action_search).isVisible = false
    }

    private fun viewModelObserver() {
        mViewModelThis.getPlaceName().observe(this, Observer {
            val placeName = it!!.substring(0, 1).toUpperCase() +
                    it.substring(1)
            textName.text = placeName
        })

        mViewModelThis.getLatitude().observe(this, Observer {
            textLat.text = it.toString()
        })

        mViewModelThis.getLongitude().observe(this, Observer {
            textLon.text = it.toString()
        })
    }

    override fun showData() { lay_detail_data.visibility = View.VISIBLE }

    override fun showLoading() { image_detail_loading.visibility = View.VISIBLE }

    override fun showErrorMessage() { lay_detail_error.visibility = View.VISIBLE  }

    override fun hideData() { lay_detail_data.visibility = View.GONE }

    override fun hideLoading() { image_detail_loading.visibility = View.GONE }

    override fun hideErrorMessage() { lay_detail_error.visibility = View.GONE }

    private fun inject(){ BajalangApp.mInstance.mAppComponent.inject(this) }

}