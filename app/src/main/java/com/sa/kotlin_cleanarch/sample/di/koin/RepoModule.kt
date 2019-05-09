package com.sa.kotlin_cleanarch.sample.di.koin

import com.sa.kotlin_cleanarch.sample.model.repo.PostRepository
import org.koin.dsl.module


/* Created by Sahil Bharti on 5/4/19.
 *
*/


val repoModule = module {

    /**Provide PostRepository class Singleton object
     * you can use it any KoinComponent class  below is declaration
     *  private val globalRepository: PostRepository by inject() */

    single { PostRepository(get(), get(), get()) }

}