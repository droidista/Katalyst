package com.droidista.katalyst.generator

import com.droidista.katalyst.css.CssDefinition
import com.droidista.katalyst.dom.Element
import com.droidista.katalyst.dom.Node
import com.droidista.katalyst.internal.ElementTreeTraversalState
import com.droidista.katalyst.environment.Environment
import com.droidista.katalyst.dom.Text
import java.util.EmptyStackException
import java.util.Stack

class InlineStyleGenerator(val cssDefinitionList: List<CssDefinition>) : DeferredGenerator {
    override fun generate(root: Node, environment: Environment): Element {
        val matchingDefinitions = extractMatchingStylesheets(root)
        val definitionsWithDependencies = resolveDependencies(matchingDefinitions)
        val generatedCss = definitionsWithDependencies.joinToString(separator = " ") { it.render() }
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
                        cssDefinition.matchers?.any { matcher ->
                            matcher.matches(element)
                        } == true
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

    private fun resolveDependencies(definitions: Collection<CssDefinition>): Set<CssDefinition> {
        val list = mutableListOf<CssDefinition>()
        list.addAll(definitions)
        var index = 0
        while (index < list.size) {
            val definition = list[index]
            val dependencyTags = definition.depends
            if (!dependencyTags.isNullOrEmpty()) {
                dependencyTags.forEach { requiredDependencyTag ->
                    val dependencies = cssDefinitionList.filter { externalDefinition ->
                        externalDefinition.dependencyTag == requiredDependencyTag
                    }
                    list.addAll(dependencies - list.toSet())
                }
            }
            index++
        }
        return list.toSet()
    }
}