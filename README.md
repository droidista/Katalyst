# Katalyst Static Site Generator

Katalyst is a simple and minimal static site generator implementation in Kotlin. This project is in pre-alpha state and no way ready for production. 


This project is organized as two modules:

* katalyst - The library implementation of static site generator
* app - A sample blog site implementation in Katalyst DSL, which generates HTML files on execution (currently nowhere near to a blog site!)

## Roadmap

➡️ A minimal, type safe DSL for building DOM trees and reusing them


➡️ A static DOM analyzer which iterates over the DOM tree and statically adds CSS rules to the HTML page


➡️ Pluggable adapters for covering common use cases like pagination, image optimization and further enhancements