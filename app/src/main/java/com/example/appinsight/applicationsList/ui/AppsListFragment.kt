package com.example.appinsight.applicationsList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.appinsight.applicationsList.ui.screens.AppsListScreen
import com.example.appinsight.main.ui.theme.ProjectTheme

class AppsListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ProjectTheme {
                    AppsListScreen()
                }
            }
        }
    }

    companion object {
        fun newInstance() = AppsListFragment()
    }
}