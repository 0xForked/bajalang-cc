package id.asmith.bajalangclean.ui.main.fragment.list.rv

import id.asmith.bajalangclean.data.remote.model.Place


/**
 * Created by Agus Adhi Sumitro on 18/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

interface DataListener {
    fun onItemClick(place: Place)
}