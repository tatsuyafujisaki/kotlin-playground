plugins {
    kotlin("jvm") version "2.1.10"
    embeddedKotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.legacy.moshi.kotlin)
    implementation(libs.legacy.rxjava)
    testImplementation(kotlin("test"))
    testImplementation(libs.coroutines.test)
}

tasks.test {
    useJUnitPlatform()
}
