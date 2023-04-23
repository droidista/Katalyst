package com.droidista.app.css

import com.droidista.katalyst.css.*

val baseCss = listOf(
    CssDefinition(
        matchers = listOf(
            Id("btn-switch-dark"),
        ),
        declarations = mapOf(
            "display" to "var(--btn-switch-dark-display)",
            "text-decoration" to "none"
        ),
        depends = listOf(":root")
    ),
    CssDefinition(
        matchers = listOf(
            Id("btn-switch-light"),
        ),
        declarations = mapOf(
            "display" to "var(--btn-switch-light-display)",
            "text-decoration" to "none"
        ),
        depends = listOf(":root")
    ),
    CssDefinition(
        matchers = listOf(
            Tag("html")
        ),
        declarations = mapOf(
            "background-color" to "var(--background-color)",
            "color" to "var(--text-color)",
            "font-family" to "'Inter', sans-serif",
            "scrollbar-gutter" to "stable both-edges"
        ),
        depends = listOf(":root", "Inter")
    ),
    CssDefinition(
        matchers = listOf(
            Tag("html")
        ),
        query = "@supports (font-variation-settings: normal)",
        declarations = mapOf(
            "font-family" to "'Inter var', sans-serif",
        ),
        depends = listOf("Inter"),
    ),
    CssDefinition(
        matchers = listOf(
            Tag("code"),
            Tag("pre")
        ),
        declarations = mapOf(
            "font-family" to "'JetBrains Mono', monospace",
            "font-variant-ligatures" to "normal"
        ),
        depends = listOf("JetBrains Mono"),
    ),
    CssDefinition(
        matchers = listOf(
            Tag("h1"),
            Tag("h2"),
            Tag("h3"),
            Tag("h4"),
            Tag("h5"),
            Tag("h6"),
            Tag("p"),
        ),
        declarations = mapOf(
            "color" to "var(--text-color)",
        ),
        depends = listOf(":root"),
    ),
    CssDefinition(
        matchers = listOf(
            Tag("li"),
            Tag("p"),
        ),
        declarations = mapOf(
            "line-height" to "1.5em",
        ),
    ),
    CssDefinition(
        matchers = listOf(
            Tag("h2"),
        ),
        declarations = mapOf(
            "margin-bottom" to "0.5rem"
        ),
    ),
    CssDefinition(
        matchers = listOf(
            Tag("a"),
        ),
        declarations = mapOf(
            "color" to "var(--link-color-normal)",
            "text-decoration" to "none"
        ),
        depends = listOf(":root")
    ),
    CssDefinition(
        matchers = listOf(
            All(tag = "a", pseudoClassNames = listOf("hover"))
        ),
        declarations = mapOf(
            "text-decoration" to "underline",
        ),
    ),
    CssDefinition(
        matchers = listOf(
            All(tag = "a", pseudoClassNames = listOf("visited"))
        ),
        declarations = mapOf(
            "color" to "var(--link-color-visited)",
        ),
        depends = listOf(":root"),
    ),
    CssDefinition(
        matchers = listOf(
            ClassName("article-date"),
        ),
        declarations = mapOf(
            "color" to "var(--secondary-text-color)",
        ),
        depends = listOf(":root"),
    ),
    CssDefinition(
        matchers = listOf(
            Tag("footer")
        ),
        declarations = mapOf(
            "color" to "var(--text-color)",
            "margin-top" to "3em",
            "margin-bottom" to "1em",
            "font-size" to  "0.8em",
        ),
        depends = listOf(":root"),
    ),
    CssDefinition(
        matchers = listOf(
            All(tag = "a", classNames = listOf("nav-menu-item")),
        ),
        declarations = mapOf(
            "display" to "inline-block",
            "color" to "var(--nav-menu-text-color)",
            "padding" to "0.5em 1em",
            "text-decoration" to "none",
            "border-radius" to "2em",
            "background-color" to "var(--nav-menu-bg-normal)",
            "border" to "1.5pt solid var(--nav-menu-text-color)",
            "font-size" to "1.1em",
        ),
        depends = listOf(":root"),
    ),
    CssDefinition(
        matchers = listOf(
            ClassName("nav-menu-item-toggle"),
        ),
        declarations = mapOf(
            "display" to "inline-block",
            "color" to "var(--nav-menu-text-color)",
            "padding" to "0.5em 1em",
            "text-decoration" to "none",
            "border-radius" to "2em",
            "background-color" to "var(--nav-menu-bg-normal)",
            "border" to "1.5pt solid var(--nav-menu-text-color)",
            "font-size" to "1.1em",
            "background-repeat" to "no-repeat",
            "background-position" to "center",
            "background-image" to "var(--btn-switch-theme-icon)",
        ),
        depends = listOf(":root"),
    ),
    CssDefinition(
        matchers = listOf(
            Tag("blockquote"),
        ),
        declarations = mapOf(
            "padding" to ".5rem 1rem",
            "margin" to ".8rem 0",
            "color" to "#7a7a7a",
            "border-left" to "0.25rem solid #f9f9f9",
        ),
    ),
    CssDefinition(
        matchers = listOf(
            Tag("blockquote"),
        ),
        selectors = listOf(
            "blockquote p:last-child"
        ),
        declarations = mapOf(
            "margin-bottom" to "0",
        ),
    ),
    CssDefinition(
        matchers = listOf(
            Tag("pre"),
        ),
        declarations = mapOf(
            "overflow-x" to "auto",
            "border-radius" to "0.5em",
        ),
    ),
    CssDefinition(
        query = "@media screen and (min-width: 38rem)",
        matchers = listOf(
            Tag("header"),
            Tag("main"),
            Tag("footer"),
        ),
        declarations = mapOf(
            "width" to "38rem",
            "margin-left" to "auto",
            "margin-right" to "auto",
        ),
    ),
    CssDefinition(
        query = "@media screen and (min-width: 38rem)",
        matchers = listOf(
            Tag("blockquote"),
        ),
        declarations = mapOf(
            "padding-right" to "5rem",
            "padding-left" to "1.25rem",
        ),
    ),
    CssDefinition(
        matchers = listOf(
            ClassName("highlight"),
        ),
        declarations = mapOf(
            "padding" to "0.1em 0.5em",
            "border-radius" to "0.5em",
            "background-color" to "var(--highlight-background-color)",
        ),
        depends = listOf("root"),
    ),
    CssDefinition(
        matchers = listOf(
            Tag("table")
        ),
        declarations = mapOf(
            "border-spacing" to "0px"
        ),
    ),
    CssDefinition(
        matchers = listOf(
            Tag("td")
        ),
        declarations = mapOf(
            "padding" to "0.5em 0em",
        ),
    ),
    CssDefinition(
        matchers = listOf(
            All(tag = "a", classNames = listOf("value")),
        ),
        declarations = mapOf(
            "display" to "block",
            "padding" to "0.2em 2em",
        ),
    ),
)