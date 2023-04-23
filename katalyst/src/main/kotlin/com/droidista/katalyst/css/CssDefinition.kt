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
            if (!selectors.isNullOrEmpty()) {
                append(selectors.joinToString())
                append(" { ")
                append(renderDeclarations())
                append("} ")
            } else {
                append(renderDeclarations())
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
}

data class Id(val id: String): CssMatcher {
    override fun matches(node: Node): Boolean {
        val nodeId = node.attributes?.get("id")
        return id == nodeId
    }
}

data class ClassName(val className: String): CssMatcher {
    override fun matches(node: Node): Boolean {
        val classNames = node.attributes?.get("class")?.split(" ")
        return classNames?.contains(className) == true
    }
}

data class Tag(val tag: String): CssMatcher {
    override fun matches(node: Node): Boolean {
        return  node.tag == tag
    }
}

data class Attribute(val key: String, val value: String?): CssMatcher {
    override fun matches(node: Node): Boolean {
        return if (value != null) {
            node.attributes?.get(key) == value
        } else {
            node.attributes?.contains(key) == true
        }
    }

}

data class Combinator(val matchers: List<CssMatcher>): CssMatcher {
    override fun matches(node: Node): Boolean {
        val reversed = matchers.reversed()
        var current: Node? = node
        reversed.forEach { matcher ->
            val isMatching = current?.let { matcher.matches(it) } == true
            if (!isMatching) return false
            current = current?.parent
        }
        return true
    }
}
data class AllOf(val matchers: List<CssMatcher>): CssMatcher {
    constructor(vararg matchers: CssMatcher): this(matchers.toList())
    override fun matches(node: Node): Boolean {
        return matchers.all { it.matches(node) }
    }
}