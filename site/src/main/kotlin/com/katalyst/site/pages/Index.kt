package com.katalyst.site.pages

import com.katalyst.dom.document
import com.katalyst.environment.Environment
import com.katalyst.extension.formattedTime
import com.katalyst.highlighter.kotlin.dom.kotlinCode
import com.katalyst.site.templates.pageTemplate
import java.io.File
import java.time.Instant
import java.time.ZoneId

suspend fun buildIndexPage(environment: Environment) {
    val indexPage = document(environment) {
        pageTemplate {
            h2("Katalyst Static Site Generator")
            span(className = "article-date") {
                text("Last updated on ")
                formattedTime(
                    epochMillis = Instant.now().toEpochMilli(),
                    timeZoneId = ZoneId.of("GMT+05:30"),
                )
            }
            p("Katalyst is a Kotlin/JVM library for developing static websites. Currently, Katalyst implements the following concepts:")
            ol {
                li("Type-safe, extendable Kotlin DSL of basic HTML elements.")
                li {
                    text("Multi-phased HTML tree builder that splits the build pipeline to several phases, ")
                    text("so that the generation of a nested tree can be postponed to the next phase ")
                    code("T + 1", className = "highlight")
                    text(" without blocking the generation of the parent tree on the current phase ")
                    code("T", className = "highlight")
                    text(". This is quite useful if the ")
                    text("nested tree generation depends on the other branches of the parent tree.")
                }
                li(
                    "CSS compiler utilising the multi-phased HTML builder " +
                            "by analyzing the HTML tree and compiles only the " +
                            "minimal set of CSS rules required to include in the page."
                )
            }
            h2("Type-safe, extendable Kotlin DSL")
            p {
                text("You can develop HTML pages with the idiomatic Kotlin code like this:")
            }
            kotlinCode(
                """
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
                """.trimIndent()
            )
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
                code("recursivelyResolveDeferredNodes", className = "highlight")
                text(" method.")
            }

            p {
                text("Here is an example of deferred style generator")
            }
            val wd = System.getProperty("user.dir")
            val file = File(wd, "/src/main/kotlin/com/katalyst/site/pages/Example.kt")
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
                text(" shown here is copied from the actual source code ")
                a(
                    href = "https://github.com/droidista/Katalyst/blob/main/site/src/main/kotlin/com/katalyst/site/pages/Example.kt",
                    text = "(Example.kt)"
                )
                text(" and embedded while building this page! ")
                text("Also, the page embedded here is the actual page rendered by Katalyst while building this website!")
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
            kotlinCode(
                """
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
            """.trimIndent()
            )
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
            h2("Get started with Katalyst")
            p {
                text("You can start your static site right now with the pre-release builds of Katalyst in Github ")
                text("Packages Maven repository or the public Maven repository.")
            }
            h3("Set up Github Packages Maven Repository")
            p {
                a(
                    href = "https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry",
                    text = "Github Packages Maven Repository",
                    customAttributes = mapOf("target" to "_blank")
                )
                text(" requires authentication using your ")
                text("Github personal access token with at least ")
                code("read:packages", className = "highlight")
                text(" scope to install Katalyst packages.")
                kotlinCode("""
                    repositories {
                        maven {
                            url = uri("https://maven.pkg.github.com/droidista/katalyst")
                            credentials {
                                username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
                                password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
                            }
                        }
                    }
                    dependencies {
                        implementation("com.katalyst:katalyst:1.0.0-SNAPSHOT")
                        implementation("com.katalyst:kotlin-syntax-highlighter-plugin:1.0.0-SNAPSHOT")
                        implementation("com.katalyst:responsive-image:1.0.0-SNAPSHOT")
                    }
                """.trimIndent())
            }
            h3("Set up Katalyst Public Maven Repository")
            p {
                text("The access to the public Maven repository is fairly easier and does not require authentication. ")
                text("It is hosted along with the Github hosted website of Katalyst. ")
                kotlinCode("""
                    repositories {
                        maven {
                            url = uri("https://katalyst.pages.dev/maven")
                        }
                    }
                    dependencies {
                        implementation("com.katalyst:katalyst:1.0.0-SNAPSHOT")
                        implementation("com.katalyst:kotlin-syntax-highlighter-plugin:1.0.0-SNAPSHOT")
                        implementation("com.katalyst:responsive-image:1.0.0-SNAPSHOT")
                    }
                """.trimIndent())
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