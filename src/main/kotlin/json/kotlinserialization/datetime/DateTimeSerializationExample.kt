package json.kotlinserialization.datetime

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.time.LocalDateTime

@Serializable
private data class MySerializable(
        @Serializable(with = LocalDateSerializer::class) val localDate: LocalDate,
        @Serializable(with = LocalDateTimeSerializer::class) val localDateTime1: LocalDateTime,
        @Serializable(with = NullableLocalDateTimeSerializer::class) val localDateTime2: LocalDateTime?,
)

private fun main() {
    val json = """
        {
            "localDate": "2025-01-02",
            "localDateTime1": "2025-01-02 12:34:56",
            "localDateTime2": ""
        }
    """.trimIndent()

    val serializable = Json.decodeFromString<MySerializable>(string = json)
    println(serializable)
}
