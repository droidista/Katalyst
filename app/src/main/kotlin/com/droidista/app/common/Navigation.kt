package com.droidista.app.common

import com.droidista.katalyst.dom.BodyContext

fun BodyContext.buildNavigation() {
    nav(className = "site-nav") {
        a(className = "nav-menu-item", href = "/index.html", text = "Home")
        //text("\n")
        text(" ")
        a(className = "nav-menu-item", href = "/archive.html", text = "Archive")
        //text("\n")
        text(" ")
        a(className = "nav-menu-item", href = "/about.html", text = "About")
        //text("\n")
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