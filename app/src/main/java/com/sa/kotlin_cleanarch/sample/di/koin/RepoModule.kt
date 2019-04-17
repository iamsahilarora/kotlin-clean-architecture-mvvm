package com.sa.kotlin_cleanarch.sample.di.koin

import com.sa.kotlin_cleanarch.sample.model.repo.GlobalRepository
import org.koin.dsl.module


/* Created by Sahil Bharti on 5/4/19.
 *
*/


val repoModule = module {

    /**Provide GlobalRepository class Singleton object
     * you can use it any KoinComponent class  below is declaration
     *  private val globalRepository: GlobalRepository by inject() */

    single { GlobalRepository(get(), get()) }

}