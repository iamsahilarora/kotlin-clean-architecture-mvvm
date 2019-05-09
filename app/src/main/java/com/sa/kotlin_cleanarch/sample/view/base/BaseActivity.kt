package com.sa.kotlin_cleanarch.sample.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.sa.kotlin_cleanarch.sample.model.local.preference.PreferenceHelper
import com.sa.kotlin_cleanarch.sample.view_model.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


abstract class BaseActivity : AppCompatActivity(), BaseInterFace, LifecycleOwner {

    private var lifecycleRegistry = LifecycleRegistry(this)

    private val baseViewModel: BaseViewModel by viewModel()

    val mPref: PreferenceHelper by inject()


    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutIdRes = layout
        if (layoutIdRes != 0) {
            setContentView(layoutIdRes)
            val binding = DataBindingUtil.setContentView(this, layoutIdRes) as ViewDataBinding
            initUI(binding)
        }
    }
}
