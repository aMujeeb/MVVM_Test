package com.example.viewholdtet.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewholdtet.model.AppUser

class SplashViewModel : BaseViewModel() {
    private var mUser : AppUser? = null
    val mTokenObserver = MutableLiveData<String>()

    init {
        mUser = AppUser()
    }

    public fun requestNewSessionToken() {
        AppUser.requestToken { isSuccess, token ->
            if (isSuccess) {
                AppUser.getInstance().request_token = token
                mTokenObserver.value = token
            } else {
                mTokenObserver.value = null
            }
        }
    }
}