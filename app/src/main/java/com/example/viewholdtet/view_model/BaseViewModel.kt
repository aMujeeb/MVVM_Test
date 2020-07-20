package com.example.viewholdtet.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val mEmpty = MutableLiveData<Boolean>().apply { value = false }
    val mProgress = MutableLiveData<Boolean>().apply { value = false }
    val mGeneralMessage = MutableLiveData<String>()
}