package serialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Serializable
data class MySerializable(
        @Serializable(with = LocalDateSerializer::class) val localDate: LocalDate,
        @Serializable(with = LocalTimeSerializer::class) val localTime: LocalTime,
        @Serializable(with = LocalDateTimeSerializer::class) val localDateTime: LocalDateTime,
)

private fun main() {
    val json = """
        {
            "localDate": "2025-01-02",
            "localTime": "12:34:56",
            "localDateTime": "2025-01-02 12:34:56"
        }       
    """.trimIndent()

    val serializable = Json.decodeFromString<MySerializable>(string = json)
    println(serializable)
}
