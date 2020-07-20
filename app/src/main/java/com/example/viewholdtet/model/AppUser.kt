package com.example.viewholdtet.model

import com.example.viewholdtet.rest.api.APIClient
import com.example.viewholdtet.rest.responses.CreateTokenResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppUser {
    var userName: String? = null
    var password: String? = null

    val isUserNameValid get() = !userName.isNullOrEmpty()
    val isPasswordValid get() = !password.isNullOrEmpty()

    @Transient
    var request_token: String? = null

    companion object {
        private var sInstance: AppUser? = null

        fun getInstance(): AppUser {
            if (sInstance == null) {
                sInstance = AppUser()
            }
            return sInstance!!
        }

        fun setInstance(instance: AppUser) {
            sInstance = instance
        }

        fun requestToken(onResult: (isSuccess: Boolean, token: String?) -> Unit) {
            APIClient.createUserToken().enqueue(object : Callback<CreateTokenResponse> {
                override fun onFailure(call: Call<CreateTokenResponse>, t: Throwable) {
                    onResult(false, null)
                }

                override fun onResponse(
                    call: Call<CreateTokenResponse>,
                    response: Response<CreateTokenResponse>
                ) {
                    if (response != null && response.isSuccessful) {
                        onResult(true, response.body()!!.request_token)
                    } else {
                        onResult(false, null)
                    }
                }
            })
        }

        fun createAuthSession(loginModel: LoginModel, onResult: (isSuccess: Boolean, token: String?) -> Unit) {
            APIClient.generateSessionWithCredentials(loginModel)
                .enqueue(object : Callback<CreateTokenResponse> {
                    override fun onFailure(call: Call<CreateTokenResponse>, t: Throwable) {
                        onResult(false, null)
                    }

                    override fun onResponse(
                        call: Call<CreateTokenResponse>,
                        response: Response<CreateTokenResponse>
                    ) {
                        if (response != null && response.isSuccessful) {
                            onResult(true, response.body()!!.request_token)
                        } else {
                            onResult(false, null)
                        }
                    }
                })
        }
    }
}