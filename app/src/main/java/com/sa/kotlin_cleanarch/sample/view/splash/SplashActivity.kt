package com.sa.kotlin_cleanarch.sample.view.splash

import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.sa.kotlin_cleanarch.sample.R
import com.sa.kotlin_cleanarch.sample.view.base.BaseActivity
import com.sa.kotlin_cleanarch.sample.model.bean.responses.GlobalSettingResponse
import com.sa.kotlin_cleanarch.sample.model.networkCall.ApiResponse
import com.sa.kotlin_cleanarch.sample.utils.Connectivity
import com.sa.kotlin_cleanarch.sample.view_model.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity() {

    private val splashViewMode: SplashViewModel by viewModel()

    private val globalSettingObserver: Observer<ApiResponse<GlobalSettingResponse>> by lazy {
        Observer<ApiResponse<GlobalSettingResponse>> {
            handleGlobalSettingResponse(it)
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initUI(binding: ViewDataBinding?) {
        hitGlobalSettingApi()
    }

    private fun hitGlobalSettingApi() {
        if (Connectivity.isConnected(this)) {
            /*** request viewModel to get data ***/
            splashViewMode.getGlobalSettingFromNetwork(splashViewMode.getGlobalRequest())
            /*** observe live data of viewModel*/
            splashViewMode.getGlobalSettingResponse().observe(this, globalSettingObserver)
        } else {
            Toast.makeText(this, resources.getString(R.string.no_network_error), Toast.LENGTH_LONG).show()
            finish()
        }
    }

    /* Response Handlers */
    private fun handleGlobalSettingResponse(it: ApiResponse<GlobalSettingResponse>) {
        when (it.status) {
            ApiResponse.Status.LOADING -> {
                //todo Show progress
            }
            ApiResponse.Status.SUCCESS -> {
                /*** todo Hide progress and  handle Success*/
                moveToNextScreen()
            }
            ApiResponse.Status.ERROR -> {
                /*** todo Hide Progress and Handle Error */
                Toast.makeText(this, it.errorMessage.toString(), Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun moveToNextScreen() {

    }


}