package converters

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object KotlinSerializationExamples {
    fun example() {
        val person = Person("Jane", 18)
        println(person)

        val json = Json.encodeToString(person)
        println(json)

        // Note that the returned value is non-nullable unlike Moshi.
        val person2: Person = Json.decodeFromString(json)
        println(person2)
    }
}
