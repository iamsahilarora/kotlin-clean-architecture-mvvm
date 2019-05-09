package com.sa.kotlin_cleanarch.sample.view_model

import com.sa.kotlin_cleanarch.sample.MyApplication
import com.sa.kotlin_cleanarch.sample.model.repo.PostRepository

class SplashViewModel constructor(app: MyApplication, postRepository: PostRepository ) :
    BaseViewModel(app, postRepository) {

}