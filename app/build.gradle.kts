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
    testImplementation(kotlin("test"))
}