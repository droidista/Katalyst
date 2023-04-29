# Katalyst Static Site Generator

Katalyst is a Kotlin/JVM library for developing static websites. Currently, Katalyst implements the following concepts:

1. A type-safe, extendable Kotlin DSL of basic HTML elements.
2. A multi-phased HTML element tree builder, where element nodes generated during phase `T` can be derived
from analyzing nodes derived during phase `T - 1`.
3. A CSS packer which traverses through the HTML element tree and includes only the relevant CSS rules in the page.

## TODO

1. A beginner friendly site that demonstrates the generation of static sites with Katalyst
2. API documentation
3. Release Katalyst as Maven artifact

## Plugins Roadmap

1. SEO
2. Responsive Images
3. Kotlin Syntax Highlighter
4. RSS feed generator
