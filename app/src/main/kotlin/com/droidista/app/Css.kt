package com.droidista.app

import com.droidista.katalyst.css.AllOf
import com.droidista.katalyst.css.ClassName
import com.droidista.katalyst.css.CssDefinition
import com.droidista.katalyst.css.Tag

val css = listOf(
    CssDefinition(
        matchers = listOf(
            Tag("html")
        ),
        selectors = listOf(
            "html"
        ),
        declarations = mapOf(
            "background-color" to "var(--background-color)",
            "color" to "var(--text-color)",
            "font-family" to "'Inter', sans-serif",
            "scrollbar-gutter" to "stable both-edges"
        ),
    ),
    CssDefinition(
        matchers = listOf(
            AllOf(
                Tag("a"),
                ClassName("nav-menu-item")
            ),
        ),
        selectors = listOf(
            "a.nav-menu-item"
        ),
        declarations = mapOf(
            "display" to "inline-block"
        ),
    )
)