package com.pramudiaputr.githubapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.pramudiaputr.githubapp.utils.SettingPreferences
import com.pramudiaputr.githubapp.ui.viewmodel.setting.SettingViewModel
import com.pramudiaputr.githubapp.ui.viewmodel.setting.SettingViewModelFactory

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = SettingPreferences.getInstance(dataStore)
        val viewModel: SettingViewModel by lazy {
            ViewModelProvider(this, SettingViewModelFactory(pref))[SettingViewModel::class.java]
        }

        viewModel.getThemeSetting().observe(this, { isDarkMode ->
            if (isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        })
    }
}