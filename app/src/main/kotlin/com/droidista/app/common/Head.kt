package com.droidista.app.common

import com.droidista.katalyst.html.HtmlContext

fun HtmlContext.siteHeadContext(title: String, description: String, imagePath: String) {
    head {
        meta(charset = "utf-8")
        title(title)
        meta(name = "viewport", content = "width=device-width, initial-scale=1, user-scalable=1")
        meta(
            name = "description",
            content = description
        )

        meta(property = "og:title", content = title)
        meta(
            property = "og:description",
            content = description
        )
        meta(property = "og:image", content = imagePath)
        meta(property = "og:image:alt", content = title)

        //<!-- Open graph : Twitter card -->
        meta(name = "twitter:card", content = "summary")
        meta(name = "twitter:title", content = title)
        meta(
            name = "twitter:description",
            content = description
        )
        meta(name = "twitter:url", content = "https://droidista.github.io/?src=twitter")
        meta(name = "twitter:image:src", content = imagePath)
        meta(name = "twitter:image:alt", content = title)
        meta(name = "twitter:creator", content = "@droidista")
        meta(name = "twitter:site", content = "@droidista")
    }
}