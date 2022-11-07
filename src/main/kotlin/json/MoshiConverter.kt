package json

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiConverter {
    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    inline fun <reified T> toJson(value: T): String = moshi.adapter(T::class.java).toJson(value)
    inline fun <reified T> fromJson(string: String) = moshi.adapter(T::class.java).fromJson(string)
}

fun main() {
    val person = Person("Jane", 18)
    println(person)

    val json = MoshiConverter.toJson(person)
    println(json)

    // Note that the returned value is nullable unlike Kotlin Serialization and Gson.
    val person2: Person? = MoshiConverter.fromJson(json)
    println(person2)
}
