package com.droidista.app

import com.droidista.app.common.buildHeadContent
import com.droidista.app.common.buildNavigation
import com.droidista.katalyst.dom.document
import com.droidista.katalyst.environment.Environment
import com.droidista.katalyst.responsiveimage.responsiveImage

suspend fun buildAboutPage(environment: Environment) {
    val aboutHtml = document(environment) {
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
                        responsiveImage(
                            src = "/images/about.png",
                            width = 192,
                            height = 192,
                            customAttributes = mapOf("style" to "display: block; margin: 3em auto 1em auto;"),
                        )
                        h2("Anand Bose", customAttributes = mapOf("style" to "text-align: center;"))
                        h2("Hello! \uD83D\uDC4B")
                        p {
                            text("I'm Anand Bose. I used to do ")
                            a(
                                href = "https://twitter.com/hashtag/AndroidDev",
                                text = "#AndroidDev",
                                customAttributes = mapOf("target" to "_blank"),
                            )
                            text(" since Android 2.3 (Gingerbread). Kotlin enthusiast since the 1.0 of the language.")
                            text("I love the Android community and so glad to be the part of it. " +
                                    "I am currently working as lead of Android team at ")
                            a(
                                href = "https://appstation.in",
                                text = "AppStation",
                                customAttributes = mapOf("target" to "_blank"),
                            )
                        }
                    }
                }
            }
        }
    }
    aboutHtml.recursivelyResolveDeferredNodes()
    aboutHtml.writeToFile("/about.html")
}