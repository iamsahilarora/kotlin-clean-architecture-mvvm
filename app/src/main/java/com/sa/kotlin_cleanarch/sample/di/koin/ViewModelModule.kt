package com.sa.kotlin_cleanarch.sample.di.koin


import com.sa.kotlin_cleanarch.sample.MyApplication
import com.sa.kotlin_cleanarch.sample.view_model.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/* Created by Sahil Bharti on 5/4/19.
 *
*/

val viewModelModule = module {

    /**Provide ViewModel object in activity Class
     * you can use it any Activity/Fragment class  below is declaration
     *
     * In Activity
     * private val baseViewModel: BaseViewModel by viewmodel() create object in activity scope
     *
     * In Fragment
     *  private val baseViewModel: BaseViewModel by viewmodel()  create object in fragment scope
     *
     *  get object of activity scope use sharedViewModel()
     *  private val baseViewModel: BaseViewModel by sharedViewmodel()
     *  */

    viewModel { BaseViewModel(androidApplication() as MyApplication, get()) }
    viewModel { SplashViewModel(androidApplication() as MyApplication, get()) }
}