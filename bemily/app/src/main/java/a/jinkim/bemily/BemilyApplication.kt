package a.jinkim.bemily

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp



/*
* Hilt를 사용하기위해 적용
* */
@HiltAndroidApp
class BemilyApplication : Application() {

    init{
        instance = this
    }

    companion object {
        var instance: BemilyApplication? = null
        fun context() : Context {
            return instance!!.applicationContext
        }
    }
}