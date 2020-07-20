package com.example.viewholdtet.rest.responses

open class CreateTokenResponse : BaseResponse() {
    var expires_at : String? = null
    var request_token : String? = null
}