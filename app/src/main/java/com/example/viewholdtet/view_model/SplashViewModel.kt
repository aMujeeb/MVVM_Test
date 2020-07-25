package com.example.viewholdtet.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewholdtet.model.AppUser
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {
    private var mUser: AppUser? = null
    val mTokenObserver = MutableLiveData<String>()

    init {
        mUser = AppUser()
    }

    public fun requestNewSessionToken() {

        viewModelScope.launch {
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
}