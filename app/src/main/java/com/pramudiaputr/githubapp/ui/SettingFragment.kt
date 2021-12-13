package com.pramudiaputr.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pramudiaputr.githubapp.dataStore
import com.pramudiaputr.githubapp.databinding.FragmentSettingBinding
import com.pramudiaputr.githubapp.utils.SettingPreferences
import com.pramudiaputr.githubapp.ui.viewmodel.setting.SettingViewModel
import com.pramudiaputr.githubapp.ui.viewmodel.setting.SettingViewModelFactory

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = SettingPreferences.getInstance(requireContext().dataStore)
        val viewModel: SettingViewModel by lazy {
            ViewModelProvider(this, SettingViewModelFactory(pref))[SettingViewModel::class.java]
        }

        viewModel.getThemeSetting().observe(viewLifecycleOwner, { isDarkMode ->
            binding.switchTheme.isChecked = isDarkMode
        })

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveThemeSetting(isChecked)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}