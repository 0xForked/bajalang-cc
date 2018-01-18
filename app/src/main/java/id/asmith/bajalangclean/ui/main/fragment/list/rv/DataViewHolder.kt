package id.asmith.bajalangclean.ui.main.fragment.list.rv

import android.location.Location
import android.support.v7.widget.RecyclerView
import android.view.View
import id.asmith.bajalangclean.R
import id.asmith.bajalangclean.data.remote.model.Place
import kotlinx.android.synthetic.main.item_place.view.*


/**
 * Created by Agus Adhi Sumitro on 18/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
class DataViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    fun bind(place: Place, listener: DataListener) {

        //Image
        if (place.category_id == 1.toString())
            itemView.LogoContent.setImageResource(R.drawable.ic_restaurant_black)
        if (place.category_id == 2.toString())
            itemView.LogoContent.setImageResource(R.drawable.ic_local_library)

        //Name
        val placeName = place.name.substring(0, 1).toUpperCase() +
                place.name.substring(1)
        itemView.txtNameContent.text = placeName

        //Distance
        val content = Location("content")
        content.latitude = place.lat
        content.longitude = place.lon
        val device = Location("device")
        device.latitude = 0.7368422
        device.longitude = 124.3155934
        var distance = content.distanceTo(device)
        var dist = distance.toString() + "M"
        if (distance > 1000.0f) {
            distance /= 1000.0f
            dist = distance.toString() + "KM"
            itemView.txtLength.text = dist
        }
        itemView.txtLength.text = dist

        //onClick
        itemView.layContentClick.setOnClickListener{ listener.onItemClick(place) }

    }
}