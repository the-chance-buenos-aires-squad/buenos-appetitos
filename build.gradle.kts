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
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}