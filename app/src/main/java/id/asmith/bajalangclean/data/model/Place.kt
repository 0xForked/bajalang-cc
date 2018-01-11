package id.asmith.bajalangclean.data.model


/**
 * Created by Agus Adhi Sumitro on 10/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */



data class Place(val id: Int,
                 val location_id: String,
                 val category_id: String,
                 val name: String,
                 val lat: Double,
                 val lon: Double)

