package com.droidista.katalyst.html

class HeadContext(val node: Node) : BaseContext() {
    fun title(title: String) {
        val node = Node(
            tag = "title",
            parent = node,
        )
        node.children = listOf(
            Text(
                text = title,
                parent = node,
            ),
        )
        elements.add(node)
    }

    fun meta(
        charset: String? = null,
        name: String? = null,
        property: String? = null,
        content: String? = null,
    ) {
        val node = Node(
            tag = "meta",
            parent = node,
            attributes = buildMap {
                if (charset != null) {
                    put("charset", charset)
                }
                if (name != null) {
                    put("name", name)
                }
                if (property != null) {
                    put("property", property)
                }
                if (content != null) {
                    put("content", content)
                }
            }
        )
        elements.add(node)
    }

    fun link(
        rel: String? = null,
        sizes: String? = null,
        type: String? = null,
        href: String? = null,
    ) {
        val node = Node(
            tag = "link",
            parent = node,
            attributes = buildMap {
                if (rel != null) {
                    put("rel", rel)
                }
                if (sizes != null) {
                    put("sizes", sizes)
                }
                if (type != null) {
                    put("type", type)
                }
                if (href != null) {
                    put("href", href)
                }
            }
        )
        elements.add(node)
    }

    fun script(
        src: String? = null,
        async: Boolean = false,
        defer: Boolean = false,
    ) {
        val node = Node(
            tag = "script",
            parent = node,
            attributes = buildMap {
                if (src != null) {
                    put("src", src)
                }
                if (async) {
                    put("async", null)
                }
                if (defer) {
                    put("defer", null)
                }
            },
            children = listOf(
                Text(text = "")
            ),
        )
        elements.add(node)
    }
}