package id.asmith.bajalangclean.util.reactive

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers




/**
 * Created by Agus Adhi Sumitro on 05/01/2018.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

class SchedulerProvider : SchedulerProviderNavigation {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun multi(): Scheduler = Schedulers.newThread()

}