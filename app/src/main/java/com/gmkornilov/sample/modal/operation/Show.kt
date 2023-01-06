package com.gmkornilov.sample.modal.operation

import com.bumble.appyx.core.navigation.NavKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import com.gmkornilov.sample.modal.Modal
import com.gmkornilov.sample.modal.ModalElement
import com.gmkornilov.sample.modal.ModalElements

@Parcelize
data class Show<T : Any>(
    private val element: @RawValue T,
) : ModalOperation<T> {
    override fun isApplicable(elements: ModalElements<T>): Boolean {
        return true
    }

    override fun invoke(elements: ModalElements<T>): ModalElements<T> {
        return listOf(
            ModalElement(
                key = NavKey(element),
                fromState = Modal.State.CREATED,
                targetState = Modal.State.SHOWN,
                operation = this,
            )
        )
    }
}

fun <T : Any> Modal<T>.show(element: T) {
    accept(Show(element))
}