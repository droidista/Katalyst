package com.droidista.katalyst.css

import com.droidista.katalyst.html.Node

data class CssDefinition(
    var matchers: List<CssMatcher>? = null,
    var selectors: List<String>? = null,
    var query: String? = null,
    var declarations: Map<String, String>? = null,
    var dependencyTag: String? = null,
    var depends: List<String>? = null,
) {
    fun render(): String {
        return buildString {
            val query = query
            if (query != null) {
                append(query)
                append(" { ")
                append(renderRuleSet())
                append("} ")
            } else {
                append(renderRuleSet())
            }
        }
    }

    private fun renderRuleSet(): String {
        return buildString {
            val selectors = selectors
            val matchers = matchers
            when {
                !selectors.isNullOrEmpty() -> {
                    append(selectors.joinToString())
                    append(" { ")
                    append(renderDeclarations())
                    append("} ")
                }
                !matchers.isNullOrEmpty() -> {
                    append(matchers.joinToString { it.toCssNotation() })
                    append(" { ")
                    append(renderDeclarations())
                    append("} ")
                }
                else -> {
                    append(renderDeclarations())
                }
            }
        }
    }

    private fun renderDeclarations(): String {
        return buildString {
            declarations?.forEach { (key, value) ->
                append(key)
                append(": ")
                append(value)
                append("; ")
            }
        }
    }
}

interface CssMatcher {
    fun matches(node: Node): Boolean

    fun toCssNotation(): String
}

data class Id(val id: String): CssMatcher {
    override fun matches(node: Node): Boolean {
        val nodeId = node.attributes?.get("id")
        return id == nodeId
    }

    override fun toCssNotation(): String = "#$id"
}

data class ClassName(val className: String): CssMatcher {
    override fun matches(node: Node): Boolean {
        val classNames = node.attributes?.get("class")?.split(" ")
        return classNames?.contains(className) == true
    }

    override fun toCssNotation(): String = ".$className"
}

data class Tag(val tag: String): CssMatcher {
    override fun matches(node: Node): Boolean {
        return  node.tag == tag
    }

    override fun toCssNotation(): String = tag
}

data class Attribute(val key: String, val value: String?): CssMatcher {
    override fun matches(node: Node): Boolean {
        return if (value != null) {
            node.attributes?.get(key) == value
        } else {
            node.attributes?.contains(key) == true
        }
    }

    override fun toCssNotation(): String {
        return buildString {
            append("[")
            append(key)
            if (value != null) {
                append("=")
                append("\"$value\"")
            }
            append("]")
        }
    }
}

data class All(
    val tag: String? = null,
    val classNames: List<String>? = null,
    val pseudoClassNames: List<String>? = null,
    val id: String? = null,
): CssMatcher {

    override fun matches(node: Node): Boolean {
        val isTagMatch = tag == null || node.tag == tag
        val isClassNamesMatch = if (!classNames.isNullOrEmpty()) {
            val nodeClassNames = node.attributes?.get("class")?.split(" ")
            nodeClassNames == null || nodeClassNames.containsAll(classNames)
        } else true
        val isIdMatch = id == null || node.attributes?.get("id") == id
        return isTagMatch && isClassNamesMatch && isIdMatch
    }

    override fun toCssNotation(): String {
        return buildString {
            if (tag != null) {
                append(tag)
            }
            pseudoClassNames?.forEach { pseudoClassName ->
                append(":$pseudoClassName")
            }
            classNames?.forEach { className ->
                append(".$className")
            }
            if (id != null) {
                append("#$id")
            }
        }
    }
}