package json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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

private fun sample1() {
    readAndPrint("data/object-of-numbers.json") {
        MoshiConverter.getMapAdapter<Double>().fromJson(it)
    }
}

private fun sample2() {
    readAndPrint("data/object.json") {
        MoshiConverter.getMapAdapter<String>().fromJson(it)
    }
}

private fun sample3() {
    readAndPrint("data/array.json") {
        MoshiConverter.getListAdapter<Double>().fromJson(it)
    }
}

fun sample4() {
    readAndPrint("data/comprehensive-object.json") {
        MoshiConverter.fromJson<MySerializable>(it)
    }
}

fun sample5() {
    readAndPrint("data/comprehensive-array.json") {
        MoshiConverter.getListAdapter<MySerializable2>().fromJson(it)
    }
}

fun main() {
    sample1()
    sample2()
    sample3()
    sample4()
    sample5()
}
