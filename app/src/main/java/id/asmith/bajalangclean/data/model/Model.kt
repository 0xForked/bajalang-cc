package id.asmith.bajalangclean.data.model


/**
 * Created by Agus Adhi Sumitro on 10/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

object Model {

    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)

}