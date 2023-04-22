package com.droidista.katalyst.html

class BodyContext(val node: Node) : BaseContext() {
    inline fun container(
        tag: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) {
        val node = Node(
            tag = tag,
            parent = node,
            attributes = buildMap {
                if (id != null) {
                    put("id", id)
                }
                if (className != null) {
                    put("class", className)
                }
                if (customAttributes != null) {
                    putAll(customAttributes)
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
        )
        node.children = listOf(
            Text(
                text = text,
                parent = node,
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
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("div", id, className, customAttributes, block)

    inline fun pre(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("pre", id, className, customAttributes, block)

    inline fun p(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("p", id, className, customAttributes, block)

    inline fun code(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("code", id, className, customAttributes, block)

    inline fun nav(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("nav", id, className, customAttributes, block)

    inline fun header(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("header", id, className, customAttributes, block)

    inline fun footer(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("footer", id, className, customAttributes, block)

    inline fun main(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("main", id, className, customAttributes, block)

    inline fun article(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("article", id, className,customAttributes, block)

    inline fun span(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("span", id, className, customAttributes, block)

    inline fun table(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("table", id, className, customAttributes, block)

    inline fun tr(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("tr", id, className,customAttributes, block)

    inline fun tbody(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("tbody", id, className, customAttributes, block)

    inline fun td(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) = container("td", id, className, customAttributes, block)

    inline fun a(
        id: String? = null,
        className: String? = null,
        href: String? = null,
        customAttributes: Map<String, String?>? = null,
        crossinline block: BodyContext.() -> Unit,
    ) {
        val node = Node(
            tag = "a",
            parent = node,
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
                if (customAttributes != null) {
                    putAll(customAttributes)
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
        text: String,
        customAttributes: Map<String, String?>? = null,
    ) {
        val node = Node(
            tag = "a",
            parent = node,
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
                if (customAttributes != null) {
                    putAll(customAttributes)
                }
            },
        )
        node.children = listOf(
            Text(
                text = text,
                parent = node,
            ),
        )
        elements.add(node)
    }

    fun text(text: String) {
        val node = Text(
            text = text,
            parent = node,
        )
        elements.add(node)
    }

    fun br() {
        val node = Node(
            tag = "br",
            parent = node,
        )
        elements.add(node)
    }
}