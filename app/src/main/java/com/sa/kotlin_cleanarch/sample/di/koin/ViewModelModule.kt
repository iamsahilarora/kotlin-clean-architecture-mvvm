package com.sa.kotlin_cleanarch.sample.di.koin


import com.sa.kotlin_cleanarch.sample.view_model.BaseViewModel
import com.sa.kotlin_cleanarch.sample.view_model.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/** Created by Sahil Bharti on 5/4/19.
 *
 * Copyright (c) 2019 Sahil Inc. All rights reserved.
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

    viewModel { BaseViewModel() }
    viewModel { SplashViewModel(get()) }
}