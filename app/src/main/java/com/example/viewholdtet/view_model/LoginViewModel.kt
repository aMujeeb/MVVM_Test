package com.example.viewholdtet.view_model

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewholdtet.model.AppUser
import com.example.viewholdtet.model.LoginModel
import com.example.viewholdtet.utils.LoggerUtils
import com.example.viewholdtet.utils.ViewTesterConstants
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    private var mUser: AppUser? = null
    val mErrorObserver = MutableLiveData<Int>()
    val mTokenObserver = MutableLiveData<String>()

    init {
        mUser = AppUser.getInstance()
    }

    fun onLoginClicked(v: View) {
        LoggerUtils.logMessage("Click Login")
        if (!mUser!!.isUserNameValid) {
            mErrorObserver.value = ViewTesterConstants.USER_NAME_ERROR
        } else if (!mUser!!.isPasswordValid) {
            mErrorObserver.value = ViewTesterConstants.PASSWORD_ERROR
        } else {
            generateAuthSession()
        }
    }

    val userNameWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mUser!!.userName = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

    val passwordWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mUser!!.password = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

    private fun generateAuthSession() {
        mProgress.value = true
        var loginModel = LoginModel(mUser!!.userName, mUser!!.password, mUser!!.request_token)

        viewModelScope.launch {
            AppUser.createAuthSession(loginModel) { isSuccess, token ->
                mProgress.value = false
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