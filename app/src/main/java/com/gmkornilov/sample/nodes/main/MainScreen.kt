package com.gmkornilov.sample.nodes.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node

class MainScreen(
    buildContext: BuildContext,
    private val onButtonClick: () -> Unit,
) : Node(buildContext = buildContext)  {
    @Composable
    override fun View(modifier: Modifier) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Box {
                Button(
                    onClick = onButtonClick,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "Show bottom sheet")
                }
            }
        }
    }
}