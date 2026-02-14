plugins {
    kotlin("jvm") version "+"
    embeddedKotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(23)
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.kotlin.codegen)
    implementation(libs.rxjava)
    testImplementation(kotlin("test"))
    testImplementation(libs.coroutines.test)
}

tasks.test {
    useJUnitPlatform()
}
