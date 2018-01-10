package id.asmith.bajalangclean.util.reactive

import io.reactivex.Scheduler




/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

interface SchedulerProviderNavigation {

    fun ui(): Scheduler

    fun multi(): Scheduler

}
