package json.kotlinserialization.datetime

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.time.LocalDateTime

@Serializable
private data class MySerializable(
        @Serializable(with = LocalDateTimeSerializer::class) val dateTime1: LocalDateTime,
        @Serializable(with = NullableLocalDateTimeSerializer::class) val dateTime2: LocalDateTime?,
)

private fun main() {
    val json = """
        {
            "dateTime1": "2025-01-02 12:34:56",
            "dateTime2": ""
        }
    """.trimIndent()

    val serializable = Json.decodeFromString<MySerializable>(string = json)
    println(serializable)
}
