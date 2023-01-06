package com.gmkornilov.sample.modal.operation

import com.bumble.appyx.core.navigation.Operation
import com.gmkornilov.sample.modal.Modal

interface ModalOperation<T>: Operation<T, Modal.State>