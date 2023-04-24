package com.droidista.katalyst.dom

import com.droidista.katalyst.environment.Environment

open class BaseContext(val environment: Environment) {
    val elements = mutableListOf<Element>()
}