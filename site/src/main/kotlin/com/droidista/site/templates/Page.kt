package com.droidista.site.templates

import com.droidista.katalyst.dom.BodyContext
import com.droidista.katalyst.dom.DocumentContext
import com.droidista.site.components.includeSiteFooter
import com.droidista.site.components.includeSiteHead
import com.droidista.site.components.includeSiteHeader

inline fun DocumentContext.pageTemplate(
    crossinline contentBlock: BodyContext.() -> Unit
) {
    html {
        includeSiteHead(
            title = "Katalyst Static Site Generator",
            description = "Katalyst is a Kotlin/JVM library for developing static websites.",
            imagePath = "https://droidista.github.io/Katalyst/twitter-card.png"
        )
        body {
            includeSiteHeader()
            main {
                contentBlock()
            }
            includeSiteFooter()
        }
    }
}