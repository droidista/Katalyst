package com.droidista.katalyst.html

class HtmlContext(val node: Node) : BaseContext() {
    fun head(block: HeadContext.() -> Unit) {
        val node = Node(
            tag = "head",
            parent = node,
        )
        val context = HeadContext(node)
        block(context)
        node.children = context.elements
        elements.add(node)
    }

    fun body(block: BodyContext.() -> Unit) {
        val node = Node(
            tag = "body",
            parent = node,
        )
        val context = BodyContext(node)
        block(context)
        node.children = context.elements
        elements.add(node)
    }
}