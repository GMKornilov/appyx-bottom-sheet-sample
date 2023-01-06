package com.gmkornilov.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeComponentActivity
import com.gmkornilov.sample.nodes.root.RootNode
import com.gmkornilov.sample.ui.theme.AppyxModalBottomSheetSampleTheme

class MainActivity : NodeComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppyxModalBottomSheetSampleTheme {
                NodeHost(integrationPoint = appyxIntegrationPoint) { buildContext ->
                    RootNode(buildContext)
                }
            }
        }
    }
}
