package com.droidista.app.common

import com.droidista.katalyst.html.BodyContext

fun BodyContext.siteNavigation() {
    nav(className = "site-nav") {
        a(className = "nav-menu-item", href = "/index.html", text = "Home")
        a(className = "nav-menu-item", href = "/archive.html", text = "Archive")
        a(className = "nav-menu-item", href = "/about.html", text = "About")
        a(
            id = "btn-switch-dark",
            className = "nav-menu-item-toggle requires-js",
            href = "javascript:switchToDarkMode();",
            ariaLabel = "Switch to dark mode",
            text = "&ThickSpace;"
        )
        a(
            id = "btn-switch-light",
            className = "nav-menu-item-toggle requires-js",
            href = "javascript:switchToLightMode();",
            ariaLabel = "Switch to light mode",
            text = "&ThickSpace;"
        )
    }
}