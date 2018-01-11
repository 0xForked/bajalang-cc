package id.asmith.bajalangclean.ui.main.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import id.asmith.bajalangclean.R
import id.asmith.bajalangclean.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_main_place.view.*

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
        val view = inflater.inflate(R.layout.fragment_main_place, container, false)
        setHasOptionsMenu(true)
        onClickedListener(view)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater){
        super.onCreateOptionsMenu(menu, inflater)
        menu!!.findItem(R.id.action_search).isVisible = false
    }

    private fun onClickedListener(view: View){

        view.layout_main_restaurant.setOnClickListener {
            mViewModel.replaceWithListPlace()
            mViewModel.setPassingCategory("restaurant")
        }

        view.layout_main_school.setOnClickListener {
            mViewModel.replaceWithListPlace()
            mViewModel.setPassingCategory("school")
        }

    }

}