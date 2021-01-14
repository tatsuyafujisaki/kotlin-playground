package converters

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Serialize and deserialize only fields that are annotated with @Expose.
 * This is useful to serialize and deserialize classes that override the fields of their parent class.
 * https://github.com/google/gson/blob/master/UserGuide.md#gsons-expose
 */
object GsonExposedFieldConverter {
    val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    fun <T> encodeToString(src: T): String = gson.toJson(src)
    inline fun <reified T> decodeFromString(json: String): T = gson.fromJson(json, T::class.java)
}
