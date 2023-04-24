package com.droidista.katalyst.internal

import com.droidista.katalyst.dom.Element

internal data class ElementTreeTraversalState(
    val elementList: MutableList<Element>,
    val index: Int
)