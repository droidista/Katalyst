package com.droidista.katalyst.html

import com.droidista.katalyst.environment.Environment
import com.droidista.katalyst.generator.DeferredGenerator
import com.droidista.katalyst.util.ElementTreeTraversalState
import java.util.*

sealed interface Element {
    fun render(): String
}
data class Node(
    var tag: String,
    var attributes: Map<String, String?>? = null,
    var children: MutableList<Element>? =  null,
    var parent: Node? = null,
) : Element {

    override fun render(): String {
        return buildString {
            append("<")
            append(tag)
            val attributes = attributes
            val children = children
            if (!attributes.isNullOrEmpty()) {
                attributes.forEach { (key, value) ->
                    if (value != null) {
                        append(" ")
                        append(key)
                        if (value != "true") {
                            append("=")
                            append("\"$value\"")
                        }
                    }
                }
            }
            if (!children.isNullOrEmpty()) {
                append(">")
                children.forEach { child ->
                    append(child.render())
                }
                append("</$tag>")
            } else {
                append("/>")
            }
        }
    }
}

data class Text(
    var text: String,
    var parent: Node? = null,
): Element {
    override fun render(): String {
        return text
    }
}

class Deferred(val generator: DeferredGenerator) : Element {
    override fun render(): String = ""

}

fun renderDeferredNodes(root: Node, environment: Environment) {
    val stack = Stack<ElementTreeTraversalState>()
    var elementList = mutableListOf<Element>(root)
    var index = 0
    while (true) {
        when (val element = elementList.getOrNull(index)) {
            is Node -> {
                val children = element.children
                if (!children.isNullOrEmpty()) {
                    stack.push(ElementTreeTraversalState(elementList, index))
                    elementList = children
                    index = 0
                    continue
                }
            }
            is Deferred -> {
                val generated = element.generator.generate(root, environment)
                elementList[index] = generated
            }
            else -> {}
        }
        index++
        if (index !in elementList.indices) {
            try {
                val stackEntry = stack.pop()
                if ((stackEntry.index + 1) in stackEntry.elementList.indices) {
                    elementList = stackEntry.elementList
                    index = stackEntry.index + 1
                }
            } catch (e: EmptyStackException) {
                break
            }
        }
    }
}