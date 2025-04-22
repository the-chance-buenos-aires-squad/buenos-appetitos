import org.jetbrains.kotlin.js.translate.context.Namer.kotlin

plugins {
    kotlin("jvm") version "2.0.20"
}


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.insert-koin:koin-core:4.0.4")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
    testImplementation("io.mockk:mockk:1.14.0")
    testImplementation("com.google.truth:truth:1.4.4")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}