package com.pramudiaputr.githubapp.ui.viewmodel.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pramudiaputr.githubapp.utils.SettingPreferences

@Suppress("UNCHECKED_CAST")
class SettingViewModelFactory(private val pref: SettingPreferences) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(pref) as T
        }

        throw IllegalArgumentException("Unknown ViewModel clas: ${modelClass.name}")
    }
}