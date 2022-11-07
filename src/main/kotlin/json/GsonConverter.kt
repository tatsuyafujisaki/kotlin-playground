package json

import com.google.gson.Gson

object GsonConverter {
    val gson = Gson()
    fun <T> toString(src: T): String = gson.toJson(src)
    inline fun <reified T> fromJson(json: String): T = gson.fromJson(json, T::class.java)
}

fun main() {
    val person = Person("Jane", 18)
    println(person)

    val json = GsonConverter.toString(person)
    println(json)

    // Note that the returned value is not nullable unlike Moshi.
    val person2: Person = GsonConverter.fromJson(json)
    println(person2)
}
