package com.droidista.katalyst.html

class BodyContext(val node: Node) : BaseContext(indentation = node.indentation + 1) {
    inline fun container(
        tag: String,
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) {
        val node = Node(
            tag = tag,
            parent = node,
            indentation = indentation,
            attributes = buildMap {
                if (id != null) {
                    put("id", id)
                }
                if (className != null) {
                    put("class", className)
                }
            }
        )
        val context = BodyContext(node)
        block(context)
        node.children = context.elements
        elements.add(node)
    }

    private fun textContainer(
        id: String? = null,
        className: String? = null,
        tag: String,
        text: String,
    ) {
        val node = Node(
            tag = tag,
            parent = node,
            indentation = indentation,
        )
        node.children = listOf(
            Text(
                text = text,
                parent = node,
                indentation = indentation + 1
            ),
        )
        elements.add(node)
    }

    fun h1(
        id: String? = null,
        className: String? = null,
        text: String
    ) = textContainer(id, className, "h1", text)

    fun h2(
        id: String? = null,
        className: String? = null,
        text: String
    ) = textContainer(id, className, "h2", text)

    fun h3(
        id: String? = null,
        className: String? = null,
        text: String
    ) = textContainer(id, className, "h3", text)

    fun h4(
        id: String? = null,
        className: String? = null,
        text: String
    ) = textContainer(id, className, "h4", text)

    fun h5(
        id: String? = null,
        className: String? = null,
        text: String
    ) = textContainer(id, className, "h5", text)

    fun h6(
        id: String? = null,
        className: String? = null,
        text: String
    ) = textContainer(id, className, "h6", text)

    fun p(
        id: String? = null,
        className: String? = null,
        text: String
    ) = textContainer(id, className, "p", text)

    inline fun div(
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("div", id, className, block)

    inline fun p(
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("p", id, className, block)

    inline fun nav(
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("nav", id, className, block)

    inline fun header(
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("header", id, className, block)

    inline fun footer(
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("footer", id, className, block)

    inline fun main(
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("main", id, className, block)

    inline fun article(
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("article", id, className, block)

    inline fun span(
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("span", id, className, block)

    inline fun table(
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("table", id, className, block)

    inline fun tr(
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("tr", id, className, block)

    inline fun tbody(
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("tbody", id, className, block)

    inline fun td(
        id: String? = null,
        className: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("td", id, className, block)

    inline fun a(
        id: String? = null,
        className: String? = null,
        href: String? = null,
        crossinline block: BodyContext.() -> Unit,
    ) {
        val node = Node(
            tag = "a",
            parent = node,
            indentation = indentation,
            attributes = buildMap {
                if (id != null) {
                    put("id", id)
                }
                if (className != null) {
                    put("class", className)
                }
                if (href != null) {
                    put("href", className)
                }
            }
        )
        val context = BodyContext(node)
        block(context)
        node.children = context.elements
        elements.add(node)
    }

    fun a(
        id: String? = null,
        className: String? = null,
        href: String? = null,
        ariaLabel: String? = null,
        text: String,
    ) {
        val node = Node(
            tag = "a",
            parent = node,
            indentation = indentation,
            attributes = buildMap {
                if (id != null) {
                    put("id", id)
                }
                if (className != null) {
                    put("class", className)
                }
                if (href != null) {
                    put("href", href)
                }
                if (ariaLabel != null) {
                    put("aria-label", ariaLabel)
                }
            },
        )
        node.children = listOf(
            Text(
                text = text,
                parent = node,
                indentation = indentation + 1
            ),
        )
        elements.add(node)
    }
}