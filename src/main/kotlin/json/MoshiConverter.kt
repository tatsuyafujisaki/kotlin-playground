package json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import util.FileUtil.readAndPrint

@Serializable
private data class Person(val name: String, val age: Int)

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

private fun showExample1() {
    val person = Person("Jane", 18)
    println(person)

    val json = Json.encodeToString(person)
    println(json)

    // Note that the returned value is not nullable unlike Moshi.
    val person2: Person = Json.decodeFromString(json)
    println(person2)
}

private fun showExample2() {
    readAndPrint("data/object-of-numbers.json") {
        MoshiConverter.getMapAdapter<Double>().fromJson(it)
    }
}

private fun showExample3() {
    readAndPrint("data/array-of-numbers.json") {
        MoshiConverter.getListAdapter<Double>().fromJson(it)
    }
}

private fun showExample4() {
    readAndPrint("data/comprehensive-object.json") {
        MoshiConverter.getAdapter<MySerializable>().fromJson(it)
    }
}

private fun showExample5() {
    readAndPrint("data/comprehensive-array.json") {
        MoshiConverter.getListAdapter<MySerializable2>().fromJson(it)
    }
}

private fun main() {
    showExample1()
    showExample2()
    showExample3()
    showExample4()
    showExample5()
}
