package json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File

object MoshiConverter {
    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    inline fun <reified T> toJson(value: T): String = moshi.adapter(T::class.java).toJson(value)
    inline fun <reified T> fromJson(string: String) = moshi.adapter(T::class.java).fromJson(string)

    inline fun <reified T> getListAdapter(): JsonAdapter<List<T>> = moshi.adapter(
        Types.newParameterizedType(
            List::class.java, T::class.java
        )
    )

    inline fun <reified T> getMapAdapter(): JsonAdapter<Map<String, T>> = moshi.adapter(
        Types.newParameterizedType(
            Map::class.java, String::class.java, T::class.java
        )
    )
}

fun sample1() {
    val json = File("data/object-of-numbers.json").readText()
    val map = MoshiConverter.getMapAdapter<Int>().fromJson(json)
    println(map)
}

fun sample2() {
    val json = File("data/object.json").readText()
    val map = MoshiConverter.getMapAdapter<String>().fromJson(json)
    println(map)
}

fun sample3() {
    val json = File("data/array.json").readText()
    val xs = MoshiConverter.getListAdapter<Int>().fromJson(json)
    println(xs)
}

fun main() {
    sample1()
    sample2()
    sample3()
}
