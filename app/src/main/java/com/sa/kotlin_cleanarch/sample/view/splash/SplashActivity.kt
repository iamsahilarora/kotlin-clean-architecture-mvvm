package com.sa.kotlin_cleanarch.sample.view.splash

import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.sa.kotlin_cleanarch.sample.R
import com.sa.kotlin_cleanarch.sample.databinding.ActivitySplashBinding
import com.sa.kotlin_cleanarch.sample.view.base.BaseActivity
import com.sa.kotlin_cleanarch.sample.model.bean.responses.ContactListResponse
import com.sa.kotlin_cleanarch.sample.model.remote.ApiResponse
import com.sa.kotlin_cleanarch.sample.utils.Connectivity
import com.sa.kotlin_cleanarch.sample.view_model.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity() {

    private val splashViewMode: SplashViewModel by viewModel()

    private val contactListObserver: Observer<ApiResponse<ContactListResponse>> by lazy {
        Observer<ApiResponse<ContactListResponse>> {
            handleGlobalSettingResponse(it)
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initUI(binding: ViewDataBinding?) {
        (binding as ActivitySplashBinding).activity=this
    }

    fun getContactList() {
        hitGetContactListAPI()

    }

    private fun hitGetContactListAPI() {
        if (Connectivity.isConnected(this)) {
            /*** request viewModel to get data ***/
            splashViewMode.getContactList(splashViewMode.getCommentList())
            /*** observe live data of viewModel*/
            splashViewMode.getCommentListResponse().observe(this, contactListObserver)
        } else {
            Toast.makeText(this, resources.getString(R.string.no_network_error), Toast.LENGTH_LONG).show()
            finish()
        }
    }

    /* Response Handlers */
    private fun handleGlobalSettingResponse(it: ApiResponse<ContactListResponse>) {
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