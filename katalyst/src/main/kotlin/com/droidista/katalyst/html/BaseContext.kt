package com.droidista.katalyst.html

import com.droidista.katalyst.util.Environment

open class BaseContext(val environment: Environment) {
    val elements = mutableListOf<Element>()
}

class DocumentContext(val environment: Environment) {
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
        val context = HtmlContext(node, environment)
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
}

inline fun document(
    environment: Environment,
    path: String,
    isOverwriteAllowed: Boolean = true,
    crossinline block: DocumentContext.() -> Unit,
): DocumentContext {
    val outputFile = environment.getAbsoluteOutputPath(path)
    val context = DocumentContext(environment)
    block(context)
    if (outputFile.isDirectory) {
        error("The path ${outputFile.absolutePath} is a dir.")
    }
    if (!isOverwriteAllowed && outputFile.exists()) {
        error("The file ${outputFile.absolutePath} exists. Set isOverwriteAllowed = true to allow overwriting file contents.")
    }
    val output = context.getHtmlRepresentation()
    if (outputFile.exists()) {
        outputFile.delete()
    }
    outputFile.createNewFile()
    outputFile.outputStream().use {
        it.write(output.toByteArray(Charsets.UTF_8))
    }
    return context
}