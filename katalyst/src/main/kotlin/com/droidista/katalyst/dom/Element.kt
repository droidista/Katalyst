package com.droidista.katalyst.dom

import com.droidista.katalyst.environment.Environment
import com.droidista.katalyst.generator.DeferredGenerator
import com.droidista.katalyst.internal.ElementTreeTraversalState
import java.util.*

sealed interface Element {
    fun render(): String
}
data class Node(
    var tagName: String,
    var attributes: Map<String, String?>? = null,
    var children: MutableList<Element>? =  null,
    var parent: Node? = null,
) : Element {

    val id : String?
        get() = attributes?.get("id")

    val classNames: List<String>?
        get() = attributes?.get("class")?.split(" ")

    override fun render(): String {
        return buildString {
            append("<")
            append(tagName)
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
                append("</$tagName>")
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

fun resolveDeferredNodes(root: Node, environment: Environment): Int {
    val stack = Stack<ElementTreeTraversalState>()
    var elementList = mutableListOf<Element>(root)
    var index = 0
    var renderCount = 0
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
                renderCount++
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
    return renderCount
}