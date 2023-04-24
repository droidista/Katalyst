package com.droidista.app.common

import com.droidista.app.css.css
import com.droidista.katalyst.generator.InlineStyleGenerator
import com.droidista.katalyst.dom.HtmlContext

fun HtmlContext.buildHeadContent(title: String, description: String, imagePath: String) {
    head {
        meta(charset = "utf-8")
        title(title)
        meta(name = "viewport", content = "width=device-width, initial-scale=1, user-scalable=1")
        meta(
            name = "description",
            content = description
        )

        // Open graph: Facebook
        meta(property = "og:title", content = title)
        meta(
            property = "og:description",
            content = description
        )
        meta(property = "og:image", content = imagePath)
        meta(property = "og:image:alt", content = title)

        // Open graph: Twitter card
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

        //link(rel = "stylesheet", href = "/styles/base.css")
        deferred(InlineStyleGenerator(css))
        link(rel = "stylesheet", href = "/styles/prism/prismjs-night-owl.css")

        link(rel = "apple-touch-icon", sizes = "57x57", href = "/images/apple-icon-57x57.png")
        link(rel = "apple-touch-icon", sizes = "60x60", href = "/images/apple-icon-60x60.png")
        link(rel = "apple-touch-icon", sizes = "72x72", href = "/images/apple-icon-72x72.png")
        link(rel = "apple-touch-icon", sizes = "76x76", href = "/images/apple-icon-76x76.png")
        link(rel = "apple-touch-icon", sizes = "114x114", href = "/images/apple-icon-114x114.png")
        link(rel = "apple-touch-icon", sizes = "120x120", href = "/images/apple-icon-120x120.png")
        link(rel = "apple-touch-icon", sizes = "144x144", href = "/images/apple-icon-144x144.png")
        link(rel = "apple-touch-icon", sizes = "152x152", href = "/images/apple-icon-152x152.png")
        link(rel = "apple-touch-icon", sizes = "180x180", href = "/images/apple-icon-180x180.png")
        link(rel = "icon", type = "image/png", sizes = "192x192", href = "/images/android-icon-192x192.png")
        link(rel = "icon", type = "image/png", sizes = "32x32", href = "/images/favicon-32x32.png")
        link(rel = "icon", type = "image/png", sizes = "96x96", href = "/images/favicon-96x96.png")
        link(rel = "icon", type = "image/png", sizes = "16x16", href = "/images/favicon-16x16.png")
        link(rel = "manifest", href = "/manifest.json")

        meta(name = "robots", content = "index, follow")

        meta(name = "msapplication-TileColor", content = "#ffffff")
        meta(name = "msapplication-TileImage", content = "/ms-icon-144x144.png")
        meta(name = "theme-color", content = "#ffffff")

        script(src = "/scripts/main.js", async = true, defer = true)
    }
}