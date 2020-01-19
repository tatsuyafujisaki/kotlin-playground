plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.6.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    /*
     * Since Kotlin 1.4, the standard library has been added automatically.
     * https://kotlinlang.org/docs/reference/whatsnew14.html#dependency-on-the-standard-library-added-by-default
     */

    /*
     * Since Kotlin 1.1.2, the versions of artifacts of the group "org.jetbrains.kotlin" have been optional and inferred from the applied plugin.
     * (No documentation about it is found.)
     */

    val coroutinesVersion = "1.6.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx3:$coroutinesVersion")

    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    testImplementation(kotlin("test"))
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
}

tasks.test {
    useJUnitPlatform()
}
