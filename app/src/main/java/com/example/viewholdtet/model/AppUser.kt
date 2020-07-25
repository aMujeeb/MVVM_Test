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

        suspend fun requestToken(onResult: (isSuccess: Boolean, token: String?) -> Unit) {
            val response = APIClient.createUserToken()
            onResult(response.isSuccessful, response.body()?.request_token)
        }

        suspend fun createAuthSession(
            loginModel: LoginModel,
            onResult: (isSuccess: Boolean, token: String?) -> Unit
        ) {
            val response = APIClient.generateSessionWithCredentials(loginModel)
            onResult(response.isSuccessful, response.body()?.request_token)
        }
    }
}