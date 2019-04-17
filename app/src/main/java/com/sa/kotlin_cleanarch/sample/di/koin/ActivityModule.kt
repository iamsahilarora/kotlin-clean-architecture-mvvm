package com.sa.kotlin_cleanarch.sample.di.koin

import com.sa.kotlin_cleanarch.sample.view.splash.SplashActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module


/* Created by Sahil Bharti on 5/4/19.
 *
*/

var splashActivityModule = module {

    scope(named<SplashActivity>()) {
        scoped {
         //todo define provide object here  which is used for splashActivty Scope only
        }
    }
}