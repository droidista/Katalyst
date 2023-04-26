package com.droidista.katalyst.dom

import com.droidista.katalyst.environment.Environment

class BodyContext(val node: Node, environment: Environment) : BaseContext(environment) {
    inline fun container(
        tag: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) {
        val node = Node(
            tagName = tag,
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
        val context = BodyContext(node, environment)
        block(context)
        node.children = context.elements
        elements.add(node)
    }

    fun textContainer(
        tag: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        text: String,
    ) {
        val node = Node(
            tagName = tag,
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
        node.children = mutableListOf(
            Text(
                text = text,
                parent = node,
            ),
        )
        elements.add(node)
    }

    fun singleNode(
        tag: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) {
        val node = Node(
            tagName = tag,
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
        elements.add(node)
    }

    fun h1(
        text: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) = textContainer("h1", id, className, customAttributes, text)

    fun h2(
        text: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) = textContainer("h2", id, className, customAttributes, text)

    fun h3(
        text: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) = textContainer("h3", id, className, customAttributes, text)

    fun h4(
        text: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) = textContainer("h4", id, className, customAttributes, text)

    fun h5(
        text: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) = textContainer("h5", id, className, customAttributes, text)

    fun h6(
        text: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) = textContainer("h6", id, className, customAttributes, text)

    fun p(
        text: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) = textContainer("p", id, className, customAttributes, text)

    inline fun div(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("div", id, className, customAttributes, block)

    inline fun pre(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("pre", id, className, customAttributes, block)

    inline fun p(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("p", id, className, customAttributes, block)

    inline fun code(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("code", id, className, customAttributes, block)

    inline fun nav(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("nav", id, className, customAttributes, block)

    inline fun header(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("header", id, className, customAttributes, block)

    inline fun footer(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("footer", id, className, customAttributes, block)

    inline fun main(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("main", id, className, customAttributes, block)

    inline fun article(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("article", id, className,customAttributes, block)

    inline fun span(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("span", id, className, customAttributes, block)

    fun span(
        text: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) = textContainer("span", id, className, customAttributes, text)

    inline fun table(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("table", id, className, customAttributes, block)

    inline fun tr(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("tr", id, className,customAttributes, block)

    inline fun tbody(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("tbody", id, className, customAttributes, block)

    inline fun td(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("td", id, className, customAttributes, block)

    inline fun a(
        id: String? = null,
        className: String? = null,
        href: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) {
        val node = Node(
            tagName = "a",
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
            }
        )
        val context = BodyContext(node, environment)
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
            tagName = "a",
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
        node.children = mutableListOf(
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
            tagName = "br",
            parent = node,
        )
        elements.add(node)
    }

    fun img(
        id: String? = null,
        className: String? = null,
        src: String? = null,
        srcSet: String? = null,
        alt: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) {
        singleNode(
            "img", id, className,
            buildMap {
                if (src != null) {
                    put("src", src)
                }
                if (srcSet != null) {
                    put("srcset", srcSet)
                }
                if (alt != null) {
                    put("alt", alt)
                }
                if (customAttributes != null) {
                    putAll(customAttributes)
                }
            }
        )
    }

    inline fun picture(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("picture", id, className, customAttributes, block)

    fun source(
        id: String? = null,
        className: String? = null,
        type: String? = null,
        src: String? = null,
        srcSet: String? = null,
        alt: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) {
        singleNode(
            "source", id, className,
            buildMap {
                if (type != null) {
                    put("type", type)
                }
                if (src != null) {
                    put("src", src)
                }
                if (srcSet != null) {
                    put("srcset", srcSet)
                }
                if (alt != null) {
                    put("alt", alt)
                }
                if (customAttributes != null) {
                    putAll(customAttributes)
                }
            }
        )
    }

    inline fun b(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("b", id, className, customAttributes, block)

    inline fun i(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("i", id, className, customAttributes, block)

    inline fun u(
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
        block: BodyContext.() -> Unit,
    ) = container("u", id, className, customAttributes, block)

    fun b(
        text: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) = textContainer("b", id, className, customAttributes, text)

    fun i(
        text: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) = textContainer("i", id, className, customAttributes, text)

    fun u(
        text: String,
        id: String? = null,
        className: String? = null,
        customAttributes: Map<String, String?>? = null,
    ) = textContainer("u", id, className, customAttributes, text)
}