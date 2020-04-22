package converters.gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonConverter {
    val gson = Gson()
    fun <T> toJson(src: T): String = gson.toJson(src)
    inline fun <reified T> fromJson(json: String): T = gson.fromJson(json, object : TypeToken<T>() {}.type)
    // Alternatively
    // inline fun <reified T> fromJson(json: String): T = gson.fromJson(json, T::class.java)
}

fun gsonSampleUsage() {
    val person1 = Person("Jane", 18)
    val json: String = JsonConverter.toJson(person1)
    println(json)

    val person2 = JsonConverter.fromJson<Person>(json)
    println(person2)
}

private data class Person(val name: String, val age: Int)