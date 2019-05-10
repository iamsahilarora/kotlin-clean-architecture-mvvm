package com.sa.kotlin_cleanarch.sample.di.koin

import com.sa.kotlin_cleanarch.sample.model.repo.ContactRepository
import org.koin.dsl.module


/* Created by Sahil Bharti on 5/4/19.
 *
*/


val repoModule = module {

    /**Provide ContactRepository class Singleton object
     * you can use it any KoinComponent class  below is declaration
     *  private val globalRepository: ContactRepository by inject() */

    single { ContactRepository(get(), get(), get()) }

}