package id.asmith.bajalangclean.util


/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
object AppConstants {

    //Prefs constant
    const val USER_LOG_STATUS = "isUserDataExist"
    const val USER_LOG_IDENTITY = "thisUserIdentity"
    const val USER_CURRENT_CITY = "thisUserCurrentCity"
    const val DEVICE_LAT = "thisUserLatitude"
    const val DEVICE_LON = "thisUserLongitude"

    //Remote url
    private const val BASE_URL = "http://192.168.43.70/PHPproject/a-open/"
    private const val NAME_AND_VERSION = "rest-service/v1.0/place/location/"
    const val API_URL = BASE_URL + NAME_AND_VERSION

}