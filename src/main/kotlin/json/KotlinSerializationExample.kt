package json

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {
    val person = Person("Jane", 18)
    println(person)

    val json = Json.encodeToString(person)
    println(json)

    // Note that the returned value is not nullable unlike Moshi.
    val person2: Person = Json.decodeFromString(json)
    println(person2)
}
