package com.droidista.app

import com.droidista.app.common.siteHeadContext
import com.droidista.app.common.siteNavigation
import com.droidista.katalyst.html.*

fun main(args: Array<String>) {
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
            }
        }
    }
    print(doc.render(isIndentationEnabled = true, isNewLineEnabled = true))
}