package com.droidista.site.pages

import com.droidista.katalyst.dom.*
import com.droidista.katalyst.environment.Environment
import com.droidista.katalyst.generator.DeferredGenerator

/**
 * This is an example of a DeferredGenerator implementation that prints the
 * HTML tree structure
 */
class HtmlTreeStructureGenerator : DeferredGenerator {
    override fun generate(root: Node, environment: Environment): Element {
        val buffer = StringBuffer()
        val elements = mutableListOf<MutableList<Element>>()
        val indices = mutableListOf<Int>()
        var children: MutableList<Element> = mutableListOf(root)
        var index = 0
        while (true) {
            when (val element = children.getOrNull(index)) {
                is Deferred -> {
                    buffer.append(" ".repeat(elements.size * 4))
                    buffer.appendLine("[deferred]")
                }
                is Node -> {
                    buffer.append(" ".repeat(elements.size * 4))
                    buffer.appendLine(element.tagName)
                    val list = element.children
                    if (!list.isNullOrEmpty()) {
                        elements.add(children)
                        indices.add(index)
                        children = list
                        index = 0
                        continue
                    }
                }
                is Text -> {
                    buffer.append(" ".repeat(elements.size * 4))
                    buffer.appendLine("\"${element.text.take(100)}\"")
                }
                null -> {
                    try {
                        children = elements.removeLast()
                        index = indices.removeLast()
                    } catch (e: NoSuchElementException) {
                        break
                    }
                }
            }
            index++
        }
        return Text(buffer.toString())
    }
}

suspend fun buildExamplePage(environment: Environment) {
    val exampleHtml = document(environment) {
        html {
            head {
                title("Hi from Katalyst!")
            }
            body {
                h1("Hello!")
                p("This HTML page is built with Katalyst in Kotlin!")
                p("Here is the structure of my HTML tree:")
                pre {
                    deferred(generator = HtmlTreeStructureGenerator())
                }
            }
        }
    }
    exampleHtml.recursivelyResolveDeferredNodes()
    exampleHtml.writeToFile("/deferred-generator-example.html")
}