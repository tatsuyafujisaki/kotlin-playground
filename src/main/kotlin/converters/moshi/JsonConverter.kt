package converters.moshi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object JsonConverter {
    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    inline fun <reified T> toJson(value: T): String = moshi.adapter(T::class.java).toJson(value)
    inline fun <reified T> fromJson(string: String) = moshi.adapter(T::class.java).fromJson(string)
}

fun moshiSampleUsage() {
    val person = Person("Jane", 18)
    val json: String = JsonConverter.toJson(person)
    println(json)

    val person2: Person? = JsonConverter.fromJson(json)
    println(person2)
}

private data class Person(val name: String, val age: Int)
