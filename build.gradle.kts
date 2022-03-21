plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(group = "org.postgresql", name = "postgresql", version = "42.+")
    implementation(group = "org.http4k", name = "http4k-core", version = "4.20.2.0")
    implementation(group = "org.http4k", name = "http4k-server-jetty", version = "4.20.2.0")
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version = "1.3.2")
    runtimeOnly(group = "org.slf4j", name = "slf4j-simple", version = "2.0.0-alpha6")
    testImplementation(kotlin("test"))
}

tasks.register<Copy>("copyRuntimeDependencies") {
    into("build/libs")
    from(configurations.runtimeClasspath)
}