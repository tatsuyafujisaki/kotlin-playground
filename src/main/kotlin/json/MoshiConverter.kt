package json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import util.FileUtil.readAndPrint

object MoshiConverter {
    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    inline fun <reified T> getAdapter(): JsonAdapter<T> = moshi.adapter(T::class.java)

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

private fun sample0() {
    val person = Person("Jane", 18)
    println(person)

    val json = Json.encodeToString(person)
    println(json)

    // Note that the returned value is not nullable unlike Moshi.
    val person2: Person = Json.decodeFromString(json)
    println(person2)
}

private fun sample1() {
    readAndPrint("data/object-of-numbers.json") {
        MoshiConverter.getMapAdapter<Double>().fromJson(it)
    }
}

private fun sample2() {
    readAndPrint("data/array-of-numbers.json") {
        MoshiConverter.getListAdapter<Double>().fromJson(it)
    }
}

private fun sample3() {
    readAndPrint("data/comprehensive-object.json") {
        MoshiConverter.getAdapter<MySerializable>().fromJson(it)
    }
}

private fun sample4() {
    readAndPrint("data/comprehensive-array.json") {
        MoshiConverter.getListAdapter<MySerializable2>().fromJson(it)
    }
}

fun main() {
    sample1()
    sample2()
    sample3()
    sample4()
}
