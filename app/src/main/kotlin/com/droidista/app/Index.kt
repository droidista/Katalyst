package com.droidista.app

import com.droidista.app.common.buildHeadContent
import com.droidista.app.common.buildNavigation
import com.droidista.katalyst.dom.document
import com.droidista.katalyst.environment.Environment
import com.droidista.katalyst.highlighter.kotlin.dom.kotlinCode

suspend fun buildIndexPage(environment: Environment) {
    val indexHtml = document(environment) {
        html {
            buildHeadContent(
                title = "Anand's Blog",
                description = "Learner and content creator focussed on Android, Kotlin, Jetpack compose, Linux and security.",
                imagePath = "/images/favicon.jpg"
            )
            body {
                header {
                    h1("Anand's Blog")
                    buildNavigation()
                }
                main {
                    article {
                        a(href = "/template.html") {
                            h2("My Research on gRPC for Android")
                        }
                        span("Published 17 April 2023", className = "article-date")
                        p {
                            text("This is a proof-of-concept of gRPC's efficiency over conventional REST APIs. ")
                            code(className = "highlight") {
                                text("me.sayHelloWorld()")
                            }
                        }
                        kotlinCode("" +
                                "// Display values with index\n" +
                                "fun printItemsWithIndices() {\n" +
                                "    values.forEachIndexed { index, item ->\n" +
                                "        println(\"Index: \$index, value: \$item\")\n" +
                                "    }\n" +
                                "}")
                    }
                }
                footer {
                    span("&copy;2023 Anand Bose. All rights reserved.")
                }
            }
        }
    }
    indexHtml.recursivelyResolveDeferredNodes()
    indexHtml.writeToFile("/index.html")
}