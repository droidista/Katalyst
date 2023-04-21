plugins {
    kotlin("jvm")
}

group = "com.droidista.katalyst"
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

dependencies {
    testImplementation(kotlin("test"))
}
