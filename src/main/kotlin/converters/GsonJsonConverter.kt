package converters

import com.google.gson.Gson

object GsonJsonConverter {
    val gson = Gson()
    fun <T> toJson(src: T): String = gson.toJson(src)
    inline fun <reified T> fromJson(src: String): T = gson.fromJson(src, T::class.java)
}
