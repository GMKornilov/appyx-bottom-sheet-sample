package com.gmkornilov.sample.modal

val <T> ModalElements<T>.active: ModalElement<T>?
    get() = lastOrNull { it.targetState == Modal.State.SHOWN }

val <T> ModalElements<T>.activeElement: T?
    get() = active?.key?.navTarget
