package com.droidista.katalyst.html

import java.io.File

open class BaseContext {
    val elements = mutableListOf<Element>()
}

class DocumentContext {
    private var rootNode: Node? = null
    fun html(
        lang: String = "en-US",
        customAttributes: Map<String, String?>? = null,
        block: HtmlContext.() -> Unit,
    ) {
        val node = Node(
            tag = "html",
            attributes = buildMap {
                put("lang", lang)
                if (customAttributes != null) {
                    putAll(customAttributes)
                }
            },
        )
        val context = HtmlContext(node)
        block(context)
        node.children = context.elements
        rootNode = node
    }

    fun getHtmlRepresentation(): String {
        val rootNode = checkNotNull(rootNode) {
            "You should build your DOM tree with html() method"
        }
        return "<!DOCTYPE html>\n${rootNode.render()}"
    }

    fun save(outputFile: File) {
        val output = getHtmlRepresentation()
        if (outputFile.exists()) {
            outputFile.delete()
        }
        outputFile.createNewFile()
        outputFile.outputStream().use {
            it.write(output.toByteArray(Charsets.UTF_8))
        }
    }
}

inline fun document(crossinline block: DocumentContext.() -> Unit): DocumentContext {
    val context = DocumentContext()
    block(context)
    return context
}
