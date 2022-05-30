plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(group = "org.postgresql", name = "postgresql", version = "42.+")
    implementation(group = "org.http4k", name = "http4k-core", version = "4.20.2.0")
    implementation(group = "org.http4k", name = "http4k-server-jetty", version = "4.20.2.0")
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version = "1.3.2")
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-datetime", version = "0.3.2")
    implementation(group = "org.slf4j", name = "slf4j-api", version = "2.0.0-alpha5")
    runtimeOnly(group = "org.slf4j", name = "slf4j-simple", version = "2.0.0-alpha5")
    testImplementation(kotlin("test"))
}

tasks.named<Jar>("jar") {
    dependsOn("copyRuntimeDependencies")
    manifest {
        attributes["Main-Class"] = "pt.isel.ls.http.HTTPServerKt"
        attributes["Class-Path"] = configurations.runtimeClasspath.get().joinToString(" ") { it.name }
    }
}
tasks.register<Copy>("copyRuntimeDependencies") {
    from(configurations.runtimeClasspath)
    into("$buildDir/libs")
}
