package com.sa.kotlin_cleanarch.sample.view_model

import com.sa.kotlin_cleanarch.sample.MyApplication
import com.sa.kotlin_cleanarch.sample.model.repo.GlobalRepository

class SplashViewModel constructor(app: MyApplication, globalRepository: GlobalRepository ) :
    BaseViewModel(app, globalRepository) {

}