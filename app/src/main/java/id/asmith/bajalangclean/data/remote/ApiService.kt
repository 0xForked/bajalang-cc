package id.asmith.bajalangclean.data.remote

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
interface ApiService {


    @GET("datas")
    fun all(): Observable<ResponseBody>

    @GET("data/{id}")
    fun one(@Path("id") id: String): Observable<ResponseBody>

}