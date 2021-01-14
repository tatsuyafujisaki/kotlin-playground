package converters

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GsonConverter {
    val gson = Gson()
    fun <T> toJson(src: T): String = gson.toJson(src)
    inline fun <reified T> fromJson(json: String): T = gson.fromJson(json, object : TypeToken<T>() {}.type)
    // Alternatively
    // inline fun <reified T> fromJson(json: String): T = gson.fromJson(json, T::class.java)
}
