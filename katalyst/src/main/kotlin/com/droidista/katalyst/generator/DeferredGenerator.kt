package com.droidista.katalyst.generator

import com.droidista.katalyst.dom.Element
import com.droidista.katalyst.dom.Node
import com.droidista.katalyst.environment.Environment

fun interface DeferredGenerator {
    fun generate(root: Node, environment: Environment): Element
}