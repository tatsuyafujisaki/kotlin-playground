package json

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import util.FileUtil

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
    FileUtil.readAndPrint("data/array-of-numbers.json") {
        Json.decodeFromString<List<Double>>(it)
    }
}

private fun sample2() {
    FileUtil.readAndPrint("data/object-of-numbers.json") {
        Json.decodeFromString<Map<String, Double>>(it)
    }
}

private fun sample3() {
    FileUtil.readAndPrint("data/comprehensive-object.json") {
        Json.decodeFromString<MySerializable>(it)
    }
}

private fun sample4() {
    FileUtil.readAndPrint("data/comprehensive-array.json") {
        Json.decodeFromString<List<MySerializable2>>(it)
    }
}

fun main() {
    sample1()
    sample2()
    sample3()
    sample4()
}
