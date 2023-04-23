package com.droidista.katalyst.html

import com.droidista.katalyst.environment.Environment

class HtmlContext(val node: Node, environment: Environment) : BaseContext(environment) {
    fun head(block: HeadContext.() -> Unit) {
        val node = Node(
            tag = "head",
            parent = node,
        )
        val context = HeadContext(node, environment)
        block(context)
        node.children = context.elements
        elements.add(node)
    }

    fun body(block: BodyContext.() -> Unit) {
        val node = Node(
            tag = "body",
            parent = node,
        )
        val context = BodyContext(node, environment)
        block(context)
        node.children = context.elements
        elements.add(node)
    }
}