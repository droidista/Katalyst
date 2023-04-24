# Katalyst Static Site Generator

Katalyst is a simple and minimal static site generator implementation in Kotlin. This project is in pre-alpha state and no way ready for production. 


This project is organized as two modules:

* katalyst - The library implementation of static site generator
* app - A sample blog site implementation in Katalyst DSL, which generates HTML files on execution (currently nowhere near to a blog site!)

## Roadmap

✅ A minimal, type safe DSL for building and reusing DOM trees.


✅ Concept of `Deferred` nodes, `DeferredGenerator` and analyzer that iterates over the entire DOM tree and replace deferred nodes with the newly generated tree.


➡️ API documentation, maven artifact publishing, project template and website for beginners to get started.


➡️ Pluggable adapters for covering common use cases like pagination, image optimization and further enhancements