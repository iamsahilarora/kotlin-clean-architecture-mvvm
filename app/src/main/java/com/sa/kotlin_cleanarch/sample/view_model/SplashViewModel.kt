package com.sa.kotlin_cleanarch.sample.view_model

import com.sa.kotlin_cleanarch.sample.MyApplication
import com.sa.kotlin_cleanarch.sample.model.repo.ContactRepository

class SplashViewModel constructor(app: MyApplication, contactRepository: ContactRepository ) :
    BaseViewModel(app, contactRepository) {

}