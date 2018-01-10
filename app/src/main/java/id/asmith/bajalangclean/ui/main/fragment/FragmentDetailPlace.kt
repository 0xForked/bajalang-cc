package id.asmith.bajalangclean.ui.main.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import id.asmith.bajalangclean.R
import id.asmith.bajalangclean.ui.main.MainViewModel

/**
 * Created by Agus Adhi Sumitro on 10/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class FragmentDetailPlace : Fragment(){

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main_detail_place, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.action_find_location).isVisible = false
        menu.findItem(R.id.action_search).isVisible = false
    }
}