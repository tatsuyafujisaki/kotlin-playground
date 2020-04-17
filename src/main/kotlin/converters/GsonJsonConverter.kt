package converters

import com.google.gson.Gson

object GsonJsonConverter {
    val gson = Gson()
    fun serialize(src: Any): String = gson.toJson(src)
    inline fun <reified T> deserialize(src: String): T = gson.fromJson(src, T::class.java)
}