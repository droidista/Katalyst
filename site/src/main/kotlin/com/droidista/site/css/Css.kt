package com.droidista.site.css

import com.droidista.katalyst.highlighter.kotlin.css.KotlinSyntaxHighlighterCss

val css = buildList {
    addAll(variablesCss)
    addAll(baseCss)
    addAll(interFontsCss)
    addAll(jetBrainsMonoFontsCss)
    addAll(KotlinSyntaxHighlighterCss)
}