package json.kotlinserialization.datetime

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = LocalDateTime::class)
object NullableLocalDateTimeSerializer : KSerializer<LocalDateTime?> {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    override fun serialize(encoder: Encoder, value: LocalDateTime?) {
        encoder.encodeString(value?.format(formatter).orEmpty())
    }

    override fun deserialize(decoder: Decoder): LocalDateTime? = runCatching {
        LocalDateTime.parse(decoder.decodeString(), formatter)
    }.getOrNull()
}
