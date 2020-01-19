package converters

import com.google.gson.Gson

/** @see GsonConverterSamples */
object GsonConverter {
    val gson = Gson()
    fun <T> encodeToString(src: T): String = gson.toJson(src)
    inline fun <reified T> decodeFromString(json: String): T = gson.fromJson(json, T::class.java)
}
