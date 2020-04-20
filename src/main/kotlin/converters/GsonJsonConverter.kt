package converters

import com.google.gson.Gson

object GsonJsonConverter {
    val gson = Gson()
    fun <T> toJson(src: T): String = gson.toJson(src)
    inline fun <reified T> fromJson(json: String): T = gson.fromJson(json, object : TypeToken<T>() {}.type)
    // Alternatively
    // inline fun <reified T> fromJson(json: String): T = gson.fromJson(json, T::class.java)
}
