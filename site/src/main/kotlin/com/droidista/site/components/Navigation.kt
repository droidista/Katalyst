package com.droidista.site.components

import com.droidista.katalyst.dom.BodyContext

fun BodyContext.includeSiteNavigation() {
    nav(className = "site-nav") {
        a(className = "nav-menu-item", href = "https://github.com/droidista/Katalyst", text = "Github")
        text(" ")
        a(className = "nav-menu-item", href = "https://droidista.github.io/Katalyst/doc", text = "API Docs")
        text(" ")
        a(
            id = "btn-switch-dark",
            className = "nav-menu-item-toggle requires-js",
            href = "javascript:switchToDarkMode();",
            text = "&ThickSpace;",
            customAttributes = mapOf(
                "aria-label" to "Switch to dark mode"
            ),
        )
        //text("\n")
        text(" ")
        a(
            id = "btn-switch-light",
            className = "nav-menu-item-toggle requires-js",
            href = "javascript:switchToLightMode();",
            text = "&ThickSpace;",
            customAttributes = mapOf(
                "aria-label" to "Switch to dark mode"
            )
        )
    }
}