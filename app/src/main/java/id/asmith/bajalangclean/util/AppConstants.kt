package id.asmith.bajalangclean.util


/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
object AppConstants {

    //Prefs constant
    val USER_LOG_STATUS = "isUserDataExist"
    val USER_LOG_IDENTITY = "thisUserIdentity"
    val USER_CURRENT_CITY = "thisUserCurrentCity"
    val USER_LAT = "thisUserLatitude"
    val USER_LON = "thisUserLongitude"

    //Remote url
    private val BASE_URL = "http://192.168.43.70/PHPproject/a-open-project/"
    private val NAME_AND_VERSION = "rest-service/v1.0/place/location/"
    val API_URL = BASE_URL + NAME_AND_VERSION

}