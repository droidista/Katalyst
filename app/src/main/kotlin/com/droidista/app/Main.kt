package com.droidista.app

import com.droidista.app.common.siteHeadContext
import com.droidista.app.common.siteNavigation
import com.droidista.katalyst.html.*
import java.io.File

fun main(args: Array<String>) {
    val outputFile = File("/home/anandbose/Projects/blog/output.html")
    if (outputFile.exists()) {
        outputFile.delete()
    }
    outputFile.createNewFile()
    val doc = html {
        siteHeadContext(
            title = "Anand's Blog",
            description = "Learner and content creator focussed on Android, Kotlin, Jetpack compose, Linux and security.",
            imagePath = "/images/favicon.jpg"
        )
        body {
            header {
                h1(text = "Anand's Blog")
                siteNavigation()
                p {
                    text("Hello, World\nHello")
                    span {
                        text("Foo")
                    }
                    text("Foo\nBar")
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
        }
    }
    val output = doc.render()
    print(output)
    outputFile.outputStream().use {
        it.write(output.toByteArray(Charsets.UTF_8))
    }
}