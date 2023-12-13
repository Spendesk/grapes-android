package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = GrapesTheme.dimensions.spacing3, vertical = GrapesTheme.dimensions.spacing3),
                        verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing3),
                    ) {
                        GrapesButton(text = "First Compose component test")
                        Spacer(Modifier.height(GrapesTheme.dimensions.spacing3))
                        Text(text = "Shapes", style = GrapesTheme.typography.titleL)
                        Box(
                            modifier = Modifier
                                .height(100.dp)
                                .width(200.dp)
                                .background(GrapesTheme.colors.mainPrimaryDark, shape = GrapesTheme.shapes.shape0),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "shape0",
                                style = GrapesTheme.typography.titleS.copy(color = GrapesTheme.colors.structureSurface),
                            )
                        }
                        Box(
                            modifier = Modifier
                                .height(100.dp)
                                .width(200.dp)
                                .background(GrapesTheme.colors.mainPrimaryDark, shape = GrapesTheme.shapes.shape1),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "shape1",
                                style = GrapesTheme.typography.titleS.copy(color = GrapesTheme.colors.structureSurface),
                            )
                        }
                        Box(
                            modifier = Modifier
                                .height(100.dp)
                                .width(200.dp)
                                .background(GrapesTheme.colors.mainPrimaryDark, shape = GrapesTheme.shapes.shape2),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "shape2",
                                style = GrapesTheme.typography.titleS.copy(color = GrapesTheme.colors.structureSurface),
                            )
                        }
                        Box(
                            modifier = Modifier
                                .height(100.dp)
                                .width(200.dp)
                                .background(GrapesTheme.colors.mainPrimaryDark, shape = GrapesTheme.shapes.shape3),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "shape3",
                                style = GrapesTheme.typography.titleS.copy(color = GrapesTheme.colors.structureSurface),
                            )
                        }
                        Box(
                            modifier = Modifier
                                .height(100.dp)
                                .width(200.dp)
                                .background(GrapesTheme.colors.mainPrimaryDark, shape = GrapesTheme.shapes.shape4),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "shape4",
                                style = GrapesTheme.typography.titleS.copy(color = GrapesTheme.colors.structureSurface),
                            )
                        }
                    }
                }
            }
        }
}
