import org.intellij.markdown.html.URI
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
    `maven-publish`
}

group = "com.katalyst"
version = "1.0.0-SNAPSHOT"

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk9:1.6.4")
    testImplementation(kotlin("test"))
}

publishing {
    repositories {
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/droidista/Katalyst")
            credentials {
                username = project.findProperty("gpr.user") as? String ?: System.getenv("USERNAME") ?: "droidista"
                password = project.findProperty("gpr.key") as? String ?: System.getenv("TOKEN") ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        configureEach {
            includes.from("Module.md")
            sourceLink {
                remoteUrl.set(URI("https://github.com/droidista/Katalyst/blob/main/katalyst").toURL())
                localDirectory.set(projectDir)
            }
        }
    }
}