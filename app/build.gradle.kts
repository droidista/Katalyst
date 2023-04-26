plugins {
    kotlin("jvm")
    application
}

group = "com.droidista.site"
version = "1.0.0"

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

application {
    mainClass.set("com.droidista.app.MainKt")
    applicationName = "katalyst-sitegen"
}

dependencies {
    implementation(project(":katalyst"))
    implementation(project(":kotlin-syntax-highlighter-plugin"))
    implementation(project(":responsive-image"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk9:1.6.4")
    testImplementation(kotlin("test"))
}