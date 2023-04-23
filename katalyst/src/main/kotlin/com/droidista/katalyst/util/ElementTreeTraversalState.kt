package com.droidista.katalyst.util

import com.droidista.katalyst.html.Element

internal data class ElementTreeTraversalState(
    val elementList: MutableList<Element>,
    val index: Int
)