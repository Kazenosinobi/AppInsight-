package com.example.appinsight.applicationInfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.appinsight.applicationInfo.ui.screens.AppInfoScreen
import com.example.appinsight.core.ui.theme.ProjectTheme

class AppInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ProjectTheme {
                    val navController = findNavController()
                    val packageName = arguments?.getString(EXTRA_PACKAGE_NAME).orEmpty()
                    AppInfoScreen(packageName = packageName, navController = navController)
                }
            }
        }
    }

    companion object {
        private const val EXTRA_PACKAGE_NAME = "package_name"
        fun createArgs(packageName: String): Bundle {
            return bundleOf(EXTRA_PACKAGE_NAME to packageName)
        }
    }
}
