package id.asmith.bajalangclean.data.remote

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
    ): Observable<ResponseBody>

    @GET("detail")
    fun detailPlace(
            @Query("id") id: Int
    ): Observable<ResponseBody>

}