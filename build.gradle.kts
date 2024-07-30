plugins {
    kotlin("jvm") version "2.0.0"
    embeddedKotlin("plugin.serialization")
    alias(libs.plugins.ktlint)
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.legacy.json)
    implementation(libs.legacy.moshi.kotlin)
    implementation(libs.legacy.rxjava)
    testImplementation(kotlin("test"))
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockito)
}

tasks.test {
    useJUnitPlatform()
}
