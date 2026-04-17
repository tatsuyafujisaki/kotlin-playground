plugins {
    kotlin("jvm") version "+"
    embeddedKotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(kotlin("test"))
    testImplementation(libs.coroutines.test)
}

tasks.test {
    useJUnitPlatform()
}
