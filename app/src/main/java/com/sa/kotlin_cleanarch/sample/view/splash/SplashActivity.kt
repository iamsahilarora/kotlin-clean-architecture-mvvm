package com.sa.kotlin_cleanarch.sample.view.splash



import android.app.ProgressDialog
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.sa.kotlin_cleanarch.sample.R
import com.sa.kotlin_cleanarch.sample.databinding.ActivitySplashBinding
import com.sa.kotlin_cleanarch.sample.model.bean.responses.ContactListResponse
import com.sa.kotlin_cleanarch.sample.model.remote.ApiResponse
import com.sa.kotlin_cleanarch.sample.utils.Connectivity
import com.sa.kotlin_cleanarch.sample.view.base.BaseActivity
import com.sa.kotlin_cleanarch.sample.view.contact.ContactListActivity
import com.sa.kotlin_cleanarch.sample.view_model.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity() ,ContactsInteractor{

    private val splashViewMode: SplashViewModel by viewModel()

    private  lateinit var binding:ActivitySplashBinding

    private val contactListObserver: Observer<ApiResponse<ContactListResponse>> by lazy {
        Observer<ApiResponse<ContactListResponse>> {
            handleGlobalSettingResponse(it)
        }
    }

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(this).apply {
            setMessage("Loading Please wait...")
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initUI(binding: ViewDataBinding?) {
        this.binding =binding as ActivitySplashBinding
        binding.contactInteract = this
    }

    override fun getContactList() {
        hitGetContactListAPI()

    }

    private fun hitGetContactListAPI() {
        if (Connectivity.isConnected(this)) {
            /*** request viewModel to get data ***/
            splashViewMode.getContactList(splashViewMode.createContactRequest())
            /*** observe live data of viewModel*/
            splashViewMode.getCommentListResponse().observe(this, contactListObserver)
        } else {
            Toast.makeText(this, resources.getString(R.string.no_network_error), Toast.LENGTH_LONG).show()
            finish()
        }
    }

    /* Response Handlers */
    private fun handleGlobalSettingResponse(response: ApiResponse<ContactListResponse>) {
        when (response.status) {
            ApiResponse.Status.LOADING -> {
                progressDialog.show()
            }
            ApiResponse.Status.SUCCESS -> {
                progressDialog.dismiss()
                moveToNextScreen(response.data)
            }
            ApiResponse.Status.ERROR -> {
                progressDialog.dismiss()
                Toast.makeText(this, response.errorMessage.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun moveToNextScreen(data: ContactListResponse?) {

        if (data?.data != null) {
            ContactListActivity.open(this, data.data!!)
        } else {
            ContactListActivity.open(this, ArrayList())
        }

    }


}