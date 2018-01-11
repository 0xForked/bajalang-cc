package id.asmith.bajalangclean.ui.main.fragment.list


/**
 * Created by Agus Adhi Sumitro on 11/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

interface FragmentListNavigation {

    fun hideErrorMessage()

    fun showErrorMessage()

    fun hideList()

    fun showList()

    fun loadingShow()

    fun loadingHide()

    fun initAdapter()

}