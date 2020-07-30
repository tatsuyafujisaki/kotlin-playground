package converters

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

/**
 * Serialize and deserialize only fields that are annotated with @Expose.
 * This is useful to serialize and deserialize classes that override the fields of their parent class.
 * https://github.com/google/gson/blob/master/UserGuide.md#gsons-expose
 */
object GsonExposedFieldConverter {
    val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    fun <T> toJson(src: T): String = gson.toJson(src)
    inline fun <reified T> fromJson(json: String): T = gson.fromJson(json, object : TypeToken<T>() {}.type)
}
