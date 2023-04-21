package com.droidista.katalyst.html

open class BaseContext(val indentation: Int) {
    val elements = mutableListOf<Element>()
}

fun html(lang: String = "en-US", block: HtmlContext.() -> Unit): Node {
    val node = Node(
        tag = "html",
        attributes = mapOf(
            "lang" to lang
        ),
    )
    val context = HtmlContext(node)
    block(context)
    node.children = context.elements
    return node
}

