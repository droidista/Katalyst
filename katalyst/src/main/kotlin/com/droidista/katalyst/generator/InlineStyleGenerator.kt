package com.droidista.katalyst.generator

import com.droidista.katalyst.css.CssDefinition
import com.droidista.katalyst.html.Element
import com.droidista.katalyst.html.Node
import com.droidista.katalyst.util.ElementTreeTraversalState
import com.droidista.katalyst.environment.Environment
import com.droidista.katalyst.html.Text
import java.util.EmptyStackException
import java.util.Stack

class InlineStyleGenerator(val cssDefinitionList: List<CssDefinition>) : DeferredGenerator {
    override fun generate(root: Node, environment: Environment): Element {
        val matchingDefinitions = extractMatchingStylesheets(root)
        resolveDependencies(matchingDefinitions)
        val generatedCss = matchingDefinitions.joinToString(separator = " ") { it.render() }
        val node = Node("style")
        node.children = mutableListOf(
            Text(text = generatedCss, parent = node)
        )
        return node
    }

    private fun extractMatchingStylesheets(root: Node): MutableSet<CssDefinition> {
        val cssSet = mutableSetOf<CssDefinition>()
        val stack = Stack<ElementTreeTraversalState>()
        var elementList = mutableListOf<Element>(root)
        var index = 0
        while (true) {
            val element = elementList.getOrNull(index)
            if (element is Node) {
                cssSet.addAll(
                    cssDefinitionList.filter { cssDefinition ->
                        cssDefinition.matchers.any { matcher ->
                            matcher.matches(element)
                        }
                    }
                )
                val children = element.children
                if (!children.isNullOrEmpty()) {
                    stack.push(ElementTreeTraversalState(elementList, index))
                    elementList = children
                    index = 0
                    continue
                }
            }
            index++
            if (index !in elementList.indices) {
                try {
                    val stackEntry = stack.pop()
                    if ((stackEntry.index + 1) in stackEntry.elementList.indices) {
                        elementList = stackEntry.elementList
                        index = stackEntry.index + 1
                    }
                } catch (e: EmptyStackException) {
                    break
                }
            }
        }
        return cssSet
    }

    private fun resolveDependencies(definitions: MutableSet<CssDefinition>) {
        val iterator = definitions.iterator()
        while (iterator.hasNext()) {
            val definition = iterator.next()
            val dependencyTags = definition.depends
            if (!dependencyTags.isNullOrEmpty()) {
                dependencyTags.forEach { requiredDependencyTag ->
                    val list = cssDefinitionList.filter { externalDefinition ->
                        externalDefinition.dependencyTag == requiredDependencyTag
                    }
                    definitions.addAll(list)
                }
            }
        }
    }
}