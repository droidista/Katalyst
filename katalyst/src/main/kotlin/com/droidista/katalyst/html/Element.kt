package com.droidista.katalyst.html

sealed interface Element {
    fun render(isIndentationEnabled: Boolean, isNewLineEnabled: Boolean): String
}
data class Node(
    var tag: String,
    var attributes: Map<String, String?>? = null,
    var children: List<Element>? =  null,
    var parent: Node? = null,
    var indentation: Int = 0,
) : Element {
    override fun render(isIndentationEnabled: Boolean, isNewLineEnabled: Boolean): String {
        return buildString {
            if (isNewLineEnabled && isIndentationEnabled) {
                append(" ".repeat(indentation * 4))
            }
            append("<")
            append(tag)
            val attributes = this@Node.attributes
            if (!attributes.isNullOrEmpty()) {
                attributes.forEach { (key, value) ->
                    append(" ")
                    append(key)
                    if (value != null) {
                        append("=")
                        append("\"$value\"")
                    }
                }
            }
//            val children = this@Node.children
            fun multiline(children: List<Element>) {
                if (isNewLineEnabled) {
                    appendLine(">")
                } else {
                    append(">")
                }
                children.forEach {
                    append(it.render(isIndentationEnabled, isNewLineEnabled))
                }
                if (isNewLineEnabled && isIndentationEnabled) {
                    append(" ".repeat(indentation * 4))
                }
                if (isNewLineEnabled) {
                    appendLine("</${tag}>")
                } else {
                    append("</${tag}>")
                }
            }
            when (val children = this@Node.children) {
                null -> {
                    if (isNewLineEnabled) {
                        appendLine("/>")
                    } else {
                        append("/>")
                    }
                }
                else -> {
                    when (children.size) {
                        0 -> {
                            if (isNewLineEnabled) {
                                appendLine("/>")
                            } else {
                                append("/>")
                            }
                        }
                        1 -> {
                            when (val child = children[0]) {
                                is Node -> multiline(children)
                                is Text -> {
                                    append(">")
                                    append(child.text)
                                    if (isNewLineEnabled) {
                                        appendLine("</${tag}>")
                                    } else {
                                        append("</${tag}>")
                                    }
                                }
                            }
                        }
                        else -> multiline(children)
                    }
                }
            }
//            if (!children.isNullOrEmpty()) {
//                if (isNewLineEnabled) {
//                    appendLine(">")
//                } else {
//                    append(">")
//                }
//                children.forEach {
//                    append(it.toString(isIndentationEnabled, isNewLineEnabled))
//                }
//                if (isNewLineEnabled && isIndentationEnabled) {
//                    append(" ".repeat(indentation * 4))
//                }
//                if (isNewLineEnabled) {
//                    appendLine("</${tag}>")
//                } else {
//                    append("</${tag}>")
//                }
//            } else {
//                if (isNewLineEnabled) {
//                    appendLine("/>")
//                } else {
//                    append("/>")
//                }
//            }
        }
    }
}

data class Text(
    var text: String,
    var parent: Node? = null,
    var indentation: Int = 0,
): Element {
    override fun render(isIndentationEnabled: Boolean, isNewLineEnabled: Boolean): String {
        return buildString {
            if (isNewLineEnabled && isIndentationEnabled) {
                append(" ".repeat(indentation * 4))
            }
            append(text)
            if (isNewLineEnabled) {
                appendLine()
            }
        }
    }
}

