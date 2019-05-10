package com.sa.kotlin_cleanarch.sample

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.facebook.stetho.Stetho
import com.sa.kotlin_cleanarch.sample.di.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class MyApplication : Application(), Application.ActivityLifecycleCallbacks {

    var mCurrencyActivity: Activity? = null
    var isBackground: Boolean = true


    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        Stetho.initializeWithDefaults(this)
        /*** start Koin DI  */
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(getModule())
        }
        registerActivityLifecycleCallbacks(this)
    }

    /*** function to get all di modules array*/
    private fun getModule(): Iterable<Module> {
        return listOf(appModule, viewModelModule,databaseModule, repoModule,splashActivityModule)
    }

    override fun onActivityPaused(activity: Activity?) {
        isBackground = true
        mCurrencyActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        isBackground = false
        mCurrencyActivity = activity
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, p1: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, p1: Bundle?) {
    }

}