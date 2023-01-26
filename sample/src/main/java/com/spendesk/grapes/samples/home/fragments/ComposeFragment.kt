package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.spendesk.grapes.compose.button.GrapesButton
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 16/05/2022, Mon
 **/
class ComposeFragment : Fragment() {

    companion object {
        fun newInstance() = ComposeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                GrapesTheme {
                    Row {
                        GrapesButton {
                            Text(text = "First Compose component test")
                        }
                    }
                }
            }
        }
}
