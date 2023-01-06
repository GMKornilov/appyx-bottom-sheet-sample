package com.gmkornilov.sample.nodes.root

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class RootNavTarget: Parcelable {
    @Parcelize
    object MainPage: RootNavTarget()

    @Parcelize
    object ModalBottomSheet: RootNavTarget()
}
