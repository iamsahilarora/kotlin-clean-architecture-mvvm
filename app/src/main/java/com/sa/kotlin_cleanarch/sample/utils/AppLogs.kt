package com.sa.kotlin_cleanarch.sample.utils

import android.util.Log
import com.sa.kotlin_cleanarch.sample.config.ConfigConstant

class AppLogs {

    companion object {
        var LOGGING_ENABLED = ConfigConstant.LOGS_ENABLED
        fun i(tag: String, msg: String?) {
            if (LOGGING_ENABLED) {
                if (msg != null)
                    Log.i(tag, msg)
            }
        }
        fun e(tag: String, msg: String?) {
            if (LOGGING_ENABLED) {
                if (msg != null)
                    Log.e(tag, msg)
            }
        }
    }
}