package com.droidista.katalyst.html

sealed interface Element {
    fun render(): String
}
data class Node(
    var tag: String,
    var attributes: Map<String, String?>? = null,
    var children: List<Element>? =  null,
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
