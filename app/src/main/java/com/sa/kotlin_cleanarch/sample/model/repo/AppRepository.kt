package com.sa.kotlin_cleanarch.sample.model.repo

import com.sa.kotlin_cleanarch.sample.model.local.preference.PreferenceConstants
import com.sa.kotlin_cleanarch.sample.model.local.preference.PreferenceHelper


/** Created by Sahil Bharti on 26/8/19.
 *
 *Copyright (c) 2019 Sahil Inc. All rights reserved.
 */
class AppRepository(private val preferences: PreferenceHelper) {
    
    fun getIsAppFirstTimeLaunched(): Boolean {
        return preferences.getBoolean(PreferenceConstants.IS_FIRST_TIME_LAUNCHED)
    }
}