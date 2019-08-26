package com.sa.kotlin_cleanarch.sample.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.sa.kotlin_cleanarch.sample.view_model.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


abstract class BaseActivity : AppCompatActivity(), BaseInterFace, LifecycleOwner {

    private val baseViewModel: BaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutIdRes = layout
        if (layoutIdRes != 0) {
            val binding = DataBindingUtil.setContentView(this, layoutIdRes) as ViewDataBinding
            initUI(binding)
        }
    }
}
