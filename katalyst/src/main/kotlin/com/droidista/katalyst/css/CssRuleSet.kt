package com.droidista.katalyst.css

data class CssRuleSet(
    var selectors: List<String>? = null,
    var query: String? = null,
    var declarations: Map<String, String>? = null,
    var dependencyTag: String? = null,
    var depends: List<String>? = null,
)