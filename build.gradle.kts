plugins {
    embeddedKotlin("jvm")
    embeddedKotlin("plugin.serialization")
    alias(libs.plugins.ktlint)
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.moshi.kotlin)
    implementation(libs.legacy.json)
    implementation(libs.rxjava)
    testImplementation(kotlin("test"))
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockito)
    testImplementation(libs.turbine)
}

tasks.test {
    useJUnitPlatform()
}
