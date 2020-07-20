package com.example.viewholdtet.utils

import android.util.Log
import com.example.viewholdtet.utils.ViewTesterConstants.Companion.APP_TAG

class LoggerUtils {
    companion object {
        fun logMessage(message : String) {
            Log.d(APP_TAG, message)
        }
    }
}