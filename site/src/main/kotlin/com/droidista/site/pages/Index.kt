package com.droidista.site.pages

import com.droidista.katalyst.dom.document
import com.droidista.katalyst.environment.Environment
import com.droidista.katalyst.highlighter.kotlin.dom.kotlinCode
import com.droidista.site.templates.pageTemplate
import java.io.File

suspend fun buildIndexPage(environment: Environment) {
    val indexPage = document(environment) {
        pageTemplate {
            h2("Katalyst Static Site Generator")
            p("Katalyst is a Kotlin/JVM library for developing static websites. Currently, Katalyst implements the following concepts:")
            ol {
                li("A type-safe, extendable Kotlin DSL of basic HTML elements.")
                li {
                    text("A multi-phased HTML element tree builder, where element nodes generated during phase ")
                    code("T", className = "highlight")
                    text(" can be derived from analyzing nodes derived during phase ")
                    code("T - 1", className = "highlight")
                    text(".")
                }
                li("A CSS packer which traverse through the HTML element tree and includes only the relevant CSS rules in the page.")
            }
            h2("Type-safe, extendable Kotlin DSL")
            p {
                text("You can develop HTML pages with the idiomatic Kotlin code like this:")
            }
            kotlinCode("""
                    suspend fun main() {
                        /* Get the current working directory
                         * The :run gradle command will set the working directory to
                         * the module directory by default.
                         */
                        val workingDirectory = System.getProperty("user.dir")
                        val environment = Environment(
                            baseDir = File(workingDirectory,"static"),
                            outputDir = File(workingDirectory,"dist"),
                        )
                        clean(environment)
                        // Copy all static assets from ./static directory to ./dist directory
                        copyStaticAssets(environment)
                        val indexPage = document(environment) {
                            html {
                                head {
                                    title("Hi from Katalyst!")
                                }
                                body {
                                    h1("Hello!")
                                    p("This HTML page is built with Katalyst in Kotlin!")
                                }
                            }
                        }
                        // Render to index.html in dist directory
                        indexPage.writeToFile("/index.html")
                    }
                """.trimIndent())
            p {
                text("Katalyst internally translates the DSL function calls to tree structure of ")
                code("Element", className = "highlight")
                text("s. The classes that implements the ")
                code("Element", className = "highlight")
                text(" interface are:")
                ul {
                    li {
                        code("Node", className = "highlight")
                        text(" - defines an HTML element with tag, attributes and nested nodes.")
                    }
                    li {
                        code("Text", className = "highlight")
                        text(" - represents a plain text body inside an element.")
                    }
                    li {
                        code("Deferred", className = "highlight")
                        text(" - represents an empty dummy node which will be replaced by the element returned by the generator, during the next phase of tree building.")
                    }
                }
            }
            h2("Phased HTML element tree generation")
            p {
                text("Katalyst introduces the ")
                code("deferred(generator: DeferredGenerator): Deferred", className = "highlight")
                text(" element to postpone the generation of a branch of HTML element tree to the next phase. ")
                text("During the next phase, the ")
                code("Deferred", className = "highlight")
                text("element can traverse through the element tree, analyze them and return an ")
                code("Element", className = "highlight")
                text(". ")
                text("The deferred element will be replaced with the generated element by the ")
                code("DocumentContext.recursivelyResolveDeferredNodes", className = "highlight")
                text(" method.")
            }

            p {
                text("Here is an example of deferred style generator")
            }
            val wd = System.getProperty("user.dir")
            val file = File(wd, "/src/main/kotlin/com/droidista/site/pages/Example.kt")
            val codeLines = file.inputStream().use { stream ->
                stream.bufferedReader(Charsets.UTF_8)
                    .readLines()
            }
            kotlinCode(codeLines.subList(6, 53).joinToString(separator = "\n"))
            p {
                text("In the page, here we add a ")
                code("deferred", className = "highlight")
                text(" element with the ")
                code("HtmlTreeStructureGenerator", className = "highlight")
                text(".")
            }
            kotlinCode(codeLines.subList(54, 73).joinToString(separator = "\n"))
            p {
                text("You can see the generated page here:")
            }
            iframe(
                title = "Page rendered with deferred generators",
                src = "./deferred-generator-example.html",
                customAttributes = buildMap {
                    put("width", "100%")
                    put("height", "300")
                    put("style", "background: #ffffff;")
                }
            )
            a(
                href = "./deferred-generator-example.html",
                text = "Open in new tab",
                customAttributes = mapOf("target" to "_blank"),
            )
            blockquote {
                text("Fun fact: The code of ")
                code("HtmlTreeStructureGenerator", className = "highlight")
                text(" shown here is the actual source code, which is read by the site generator during runtime! ")
                text("Also, the page embedded here is the actual page generated by Katalyst!")
            }
            h2("Meet InlineStyleGenerator")
            p {
                code("InlineStyleGenerator", className = "highlight")
                text(" is a built-in implementation of ")
                code("DeferredGenerator", className = "highlight")
                text(". It traverse through the HTML nodes and search for matching styles in the provided list ")
                text("of CSS rules. You need to place a ")
                code("deferred(generator = InlineStyleGenerator(cssRuleList))", className = "highlight")
                text(" inside your head element. Before rendering the web page, you should call ")
                code("recursivelyResolveDeferredNodes()", className = "highlight")
                text(" to include the optimized list of CSS rules in the ")
                code("style", className = "highlight")
                text(" element.")
            }
            p {
                text("This is how you can define CSS rules for ")
                code("InlineStyleGenerator", className = "highlight")
                text(".")
            }
            kotlinCode("""
                val myCssRules = listOf(
                    CssDefinition(
                        matchers = listOf(
                            TagName("code"),
                            TagName("pre")
                        ),
                        declarations = mapOf(
                            "font-family" to "'JetBrains Mono', monospace",
                            "font-size" to "14px",
                            "font-variant-ligatures" to "normal",
                            "line-height" to "1.7em",
                        ),
                        depends = listOf("JetBrains Mono"),
                    ),
                    CssDefinition(
                        query = "@font-face",
                        declarations = mapOf(
                            "font-family" to "'JetBrains Mono'",
                            "font-style" to "italic",
                            "font-weight" to "400",
                            "font-display" to "swap",
                            "src" to "url(/styles/fonts/jetbrains-mono/JetBrainsMono-Italic.woff2) format('truetype')",
                        ),
                        dependencyTag = "JetBrains Mono",
                    ),
                    CssDefinition(
                        query = "@font-face",
                        declarations = mapOf(
                            "font-family" to "'JetBrains Mono'",
                            "font-style" to "normal",
                            "font-weight" to "400",
                            "font-display" to "swap",
                            "src" to "url(/styles/fonts/jetbrains-mono/JetBrainsMono-Regular.woff2) format('truetype')",
                        ),
                        dependencyTag = "JetBrains Mono",
                    ),
                )
            """.trimIndent())
            p {
                text("The ")
                code("InlineStyleGenerator", className = "highlight")
                text(" tries to match a ")
                code("Node", className = "highlight")
                text(" to ")
                code("CssDefinition", className = "highlight")
                text(" by matching their ")
                code("tag name", className = "highlight")
                text(", ")
                code("class names", className = "highlight")
                text(", or by ")
                code("id", className = "highlight")
                text(" provided in the ")
                code("matchers", className = "highlight")
                text(" list. If it successfully matches with the provided criteria in the ")
                code("matchers", className = "highlight")
                text(" list, the CSS rule will be compiled and included in the generated ")
                code("style", className = "highlight")
                text(" element.")
            }
            h2("Motivation")
            p {
                text("There are a gazillion of static site generators available on the web, but I had a mixed feeling of ")
                text("their approach towards the concept of static site generation. For me, SSG should be simple HTML pages styled ")
                text("with CSS with bare minimal use of Javascript.")
            }
            p {
                text("The following things I had in my mind when I developed Katalyst")
                ul {
                    li("A type safe, context aware DSL for HTML")
                    li("Extensibility and reuse")
                    li {
                        text("Minimise Javascript by maximum utilization of server side rendering. The ")
                        code("kotlin-syntax-highligher-plugin", className = "highlight")
                        text(" in this project is a minimal replacement of prism-js, which does syntax ")
                        text("highlighting on the browser side.")
                    }
                }
            }
            blockquote {
                text("Feel free to checkout the project source code in ")
                a(href = "https://github.com/droidista/Katalyst", text = "GitHub")
                text(". If you have any comments, ideas, suggestions or anything, ")
                a(href = "mailto:anandbose16@gmail.com", text = "just let me know!")
            }
        }
    }
    indexPage.recursivelyResolveDeferredNodes()
    indexPage.writeToFile("/index.html")
}