package com.droidista.app.common

import com.droidista.katalyst.html.BodyContext

fun BodyContext.siteNavigation() {
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
            ariaLabel = "Switch to dark mode",
            text = "&ThickSpace;"
        )
        //text("\n")
        text(" ")
        a(
            id = "btn-switch-light",
            className = "nav-menu-item-toggle requires-js",
            href = "javascript:switchToLightMode();",
            ariaLabel = "Switch to light mode",
            text = "&ThickSpace;"
        )
    }
}