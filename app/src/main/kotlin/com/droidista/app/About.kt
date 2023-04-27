package com.droidista.app

import com.droidista.app.common.buildFooter
import com.droidista.app.common.buildHeadContent
import com.droidista.app.common.buildNavigation
import com.droidista.katalyst.dom.BodyContext
import com.droidista.katalyst.dom.document
import com.droidista.katalyst.environment.Environment
import com.droidista.katalyst.responsiveimage.responsiveImage

private data class ContactInfo(
    val title: String,
    val url: String,
    val hyperlink: BodyContext.() -> Unit
)

suspend fun buildAboutPage(environment: Environment) {
    val contact = listOf(
        ContactInfo(
            title = "Twitter",
            url = "https://twitter.com/droidista",
            hyperlink = {
                text("@droidista")
            }
        ),
        ContactInfo(
            title = "Mastodon",
            url = "https://mastodon.social/@anandbose",
            hyperlink = {
                text("@anandbose@mastodon.social")
            }
        ),
        ContactInfo(
            title = "Reddit",
            url = "https://reddit.com/u/anand_bose",
            hyperlink = {
                text("u/anand_bose")
            }
        ),
        ContactInfo(
            title = "Github",
            url = "https://github.com/droidista",
            hyperlink = {
                text("@droidista")
            }
        ),
        ContactInfo(
            title = "Instagram",
            url = "https://instagram.com/anandbose16",
            hyperlink = {
                text("@anandbose16")
            }
        ),
        ContactInfo(
            title = "Email",
            url = "mailto:anandbose16@gmail.com",
            hyperlink = {
                text("anandbose16@gmail.com")
            }
        ),
        ContactInfo(
            title = "GPG Key",
            url = "/gpg-key.txt",
            hyperlink = {
                text("222CA765E222EEB0DE97D5F184D8981E1D251B65")
            }
        ),
    )
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
                        p {
                            text("A few things I enjoy: ")
                            ul {
                                li("Discovering more Android stuffs (Jetpack Compose)")
                                li("Writing and talking tech")
                                li("Having fun times with family")
                                li("Cycling")
                            }
                        }
                        h3("Contact")
                        table {
                            tbody {
                                contact.forEach {
                                    tr {
                                        td(it.title)
                                        td {
                                            a(
                                                className = "value",
                                                href = it.url,
                                                customAttributes = mapOf("target" to "_blank"),
                                                block = it.hyperlink,
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                buildFooter()
            }
        }
    }
    aboutHtml.recursivelyResolveDeferredNodes()
    aboutHtml.writeToFile("/about.html")
}