package id.asmith.bajalangclean.util


/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */
object AppConstants {

    //Pattern constant
    val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    val USERNAME_PATTERN = ("^[a-z0-9_-]{3,15}\$")
    val PHONE_PATTERN = ("^[+(00)][0-9]{6,14}$")

    //Prefs constant
    val USER_LOG_STATUS = "isUserStillLogin"
    val USER_LOG_IDENTITY = "thisUserEmail"

    //Remote url
    private val BASE_URL = "http://192.168.43.70/project/"
    private val NAME_AND_VERSION = "queue-api/example/api/v1/"
    val API_URL = BASE_URL + NAME_AND_VERSION



}