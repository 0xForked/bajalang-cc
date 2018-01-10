package id.asmith.bajalangclean.ui.main.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import id.asmith.bajalangclean.R
import id.asmith.bajalangclean.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_main_place.*

/**
 * Created by Agus Adhi Sumitro on 10/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class FragmentMainPlace : Fragment(){

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        onClickedListener()

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater){
        super.onCreateOptionsMenu(menu, inflater)
        menu!!.findItem(R.id.action_search).isVisible = false
    }

    private fun onClickedListener(){

        val city = mViewModel.getCurrentCity()

        layout_main_restaurant.setOnClickListener {
            val category = "restaurant"
            mViewModel.getListPlace(city, category)
        }

        layout_main_school.setOnClickListener {
            val category = "school"
            mViewModel.getListPlace(city, category)
        }

    }
}