package com.gmkornilov.sample.nodes.root

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.navigation.model.combined.plus
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.core.node.node
import com.bumble.appyx.navmodel.backstack.BackStack
import com.gmkornilov.sample.modal.Modal
import com.gmkornilov.sample.modal.activeElement
import com.gmkornilov.sample.modal.operation.dismiss
import com.gmkornilov.sample.modal.operation.show
import com.gmkornilov.sample.nodes.main.MainScreen
import kotlinx.coroutines.flow.*

class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<RootNavTarget> = BackStack(
        initialElement = RootNavTarget.MainPage,
        savedStateMap = buildContext.savedStateMap,
    ),
    private val modal: Modal<RootNavTarget> = Modal(
        savedStateMap = buildContext.savedStateMap,
    )
) : ParentNode<RootNavTarget>(
    buildContext = buildContext,
    navModel = backStack + modal,
) {
    private val activeModalElementFlow: Flow<RootNavTarget?> =
        modal.elements.map { it.activeElement }

    override fun resolve(navTarget: RootNavTarget, buildContext: BuildContext): Node {
        return when (navTarget) {
            RootNavTarget.MainPage -> MainScreen(buildContext) {
                modal.show(RootNavTarget.ModalBottomSheet)
            }
            RootNavTarget.ModalBottomSheet -> node(buildContext) { modifier ->
                Text("Hello bottom sheet!", modifier = modifier.padding(64.dp))
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun View(modifier: Modifier) {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = { SheetContent(sheetState) },
        ) {
            Children(
                navModel = backStack,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun SheetContent(sheetState: ModalBottomSheetState) {
        val activeElement by activeModalElementFlow.collectAsState(initial = null)

        // fix for this issue - https://stackoverflow.com/questions/68623965/jetpack-compose-modalbottomsheetlayout-throws-java-lang-illegalargumentexception
        if (activeElement == null) {
            Spacer(Modifier.size(1.dp))
        }

        Children(navModel = modal) {
            children<RootNavTarget> { child ->
                var hideCalled by remember(activeElement) { mutableStateOf(false) }

                LaunchedEffect(activeElement, hideCalled) {
                    val sheetVisibility = snapshotFlow { sheetState.isVisible }
                    sheetVisibility
                        .distinctUntilChanged()
                        .drop(1)
                        .filter { isSheetVisible -> !isSheetVisible }
                        .collect { if (!hideCalled) modal.dismiss() }
                }

                LaunchedEffect(activeElement) {
                    if (activeElement != null && !sheetState.isVisible) {
                        sheetState.show()
                    } else if (activeElement == null && sheetState.isVisible) {
                        hideCalled = true
                        sheetState.hide()
                    }
                }

                child()
            }
        }
    }
}