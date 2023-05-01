plugins {
    kotlin("jvm")
}

group = "com.katalyst.responsiveimage"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    implementation(project(":katalyst"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk9:1.6.4")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}