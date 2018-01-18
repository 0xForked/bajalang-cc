package id.asmith.bajalangclean.ui.main.fragment.list.rv

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.asmith.bajalangclean.R
import id.asmith.bajalangclean.data.remote.model.Place
import java.util.*


/**
 * Created by Agus Adhi Sumitro on 10/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class DataAdapter (private val dataList : ArrayList<Place>, private val listener : DataListener) :
        RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_place, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
            holder.bind(dataList[position], listener)

    override fun getItemCount(): Int = dataList.count()

}