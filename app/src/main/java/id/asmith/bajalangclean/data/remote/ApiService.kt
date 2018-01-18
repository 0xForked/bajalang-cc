package id.asmith.bajalangclean.data.remote

import id.asmith.bajalangclean.data.remote.model.Place
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
interface ApiService {

    @GET("list")
    fun listPlace(
            @Query("city") city: String,
            @Query("category") category: String
    ): Observable<List<Place>>

    @GET("detail")
    fun detailPlace(
            @Query("id") id: Int
    ): Observable<ResponseBody>

    @GET("search")
    fun searchPlace(
            @Query("name") name: String
    ): Observable<ResponseBody>

}