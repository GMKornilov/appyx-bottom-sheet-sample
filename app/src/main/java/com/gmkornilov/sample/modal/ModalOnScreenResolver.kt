package com.gmkornilov.sample.modal

import com.bumble.appyx.core.navigation.onscreen.OnScreenStateResolver
import com.gmkornilov.sample.modal.Modal

object ModalOnScreenResolver: OnScreenStateResolver<Modal.State> {
    override fun isOnScreen(state: Modal.State): Boolean {
        return when (state) {
            Modal.State.CREATED,
            Modal.State.DESTROYED -> false
            Modal.State.SHOWN -> true
        }
    }
}