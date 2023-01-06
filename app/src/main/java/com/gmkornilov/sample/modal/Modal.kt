package com.gmkornilov.sample.modal

import com.bumble.appyx.core.navigation.BaseNavModel
import com.bumble.appyx.core.navigation.NavElements
import com.bumble.appyx.core.navigation.backpresshandlerstrategies.BackPressHandlerStrategy
import com.bumble.appyx.core.navigation.onscreen.OnScreenStateResolver
import com.bumble.appyx.core.navigation.operationstrategies.ExecuteImmediately
import com.bumble.appyx.core.navigation.operationstrategies.OperationStrategy
import com.bumble.appyx.core.state.SavedStateMap
import com.gmkornilov.sample.modal.operation.RevertBackPressHandler

private const val MODAL_KEY = "modal"

class Modal<NavTarget : Any>(
    savedStateMap: SavedStateMap?,
    key: String = MODAL_KEY,
    backPressHandler: BackPressHandlerStrategy<NavTarget, State> = RevertBackPressHandler(),
    operationStrategy: OperationStrategy<NavTarget, State> = ExecuteImmediately(),
    screenResolver: OnScreenStateResolver<State> = ModalOnScreenResolver
) : BaseNavModel<NavTarget, Modal.State>(
    savedStateMap = savedStateMap,
    operationStrategy = operationStrategy,
    backPressHandler = backPressHandler,
    screenResolver = screenResolver,
    key = key,
    finalState = State.DESTROYED,
) {

    enum class State {
        CREATED, SHOWN, DESTROYED
    }

    override val initialElements: NavElements<NavTarget, State> = emptyList()
}