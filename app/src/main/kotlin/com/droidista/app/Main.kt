package com.droidista.app

import com.droidista.app.common.buildHeadContent
import com.droidista.app.common.buildNavigation
import com.droidista.katalyst.html.*
import java.io.File

fun main(args: Array<String>) {
    val outputFile = File("/home/anandbose/Projects/blog/output.html")
    if (outputFile.exists()) {
        outputFile.delete()
    }
    outputFile.createNewFile()
    val doc = document {
        html {
            buildHeadContent(
                title = "Anand's Blog",
                description = "Learner and content creator focussed on Android, Kotlin, Jetpack compose, Linux and security.",
                imagePath = "/images/favicon.jpg"
            )
            body {
                header {
                    h1(text = "Anand's Blog")
                    buildNavigation()
                }
                main {
                    article {
                        a(href = "/template.html") {
                            h2(text = "My Research on gRPC for Android")
                        }
                        span(className = "article-date") {
                            text("Published 17 April 2023")
                        }
                        p {
                            text("This is a proof-of-concept of gRPC's efficiency over conventional REST APIs. ")
                            code(className = "highlight") {
                                text("me.sayHelloWorld()")
                            }
                        }
                        pre(className = "language-kotlin") {
                            code(className = "language-kotlin") {
                                span(className = "token keyword") {
                                    text("fun")
                                }
                                text(" ")
                                span(className = "token function") {
                                    text("greet")
                                }
                                span(className = "token punctuation") {
                                    text("() {")
                                }
                                text("\n   ")
                                span(className = "token function") {
                                    text("println")
                                }
                                span(className = "token punctuation") {
                                    text("(")
                                }
                                span(className = "token string") {
                                    text("\"Hello, world\"")
                                }
                                span(className = "token punctuation") {
                                    text(")")
                                }
                                text("\n")
                                span(className = "token punctuation") {
                                    text("}")
                                }
                            }
                        }
                    }
                }
                footer {
                    span {
                        text("&copy;2023 Anand Bose. All rights reserved.")
                    }
                }
            }
        }
    }
    print(doc.getHtmlRepresentation())
    doc.save(outputFile)
}