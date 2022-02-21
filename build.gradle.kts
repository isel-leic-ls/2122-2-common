import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jvmVersion = "11"
val kotlinPluginVersion = "1.6.10"

group = "org.example"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.6.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(fileTree("vendor/main") { this.include("*.jar") })
    testImplementation(fileTree("vendor/test") { this.include("*.jar") })
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinPluginVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    sourceCompatibility = jvmVersion
    targetCompatibility = jvmVersion
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = jvmVersion
    }
    sourceCompatibility = jvmVersion
    targetCompatibility = jvmVersion
}