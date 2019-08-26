package com.sa.kotlin_cleanarch.sample.view_model


import androidx.lifecycle.ViewModel
import com.sa.kotlin_cleanarch.sample.model.repo.AppRepository
import org.koin.core.KoinComponent
import org.koin.core.inject


/* Created by Sahil Bharti on 5/4/19.
 *
*/
open class BaseViewModel : ViewModel(),KoinComponent {

    /**
     *  ApplicationRepository is injected here to access application level
     *  functions & preference
     *
     */
    val appRepo: AppRepository by inject()

}