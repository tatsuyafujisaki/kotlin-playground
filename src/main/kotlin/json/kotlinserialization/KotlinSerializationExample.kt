package json.kotlinserialization

import json.MySerializable
import json.MySerializable2
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import util.FileUtil

@Serializable
private data class Person(val name: String, val age: Int)

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
    FileUtil.readAndPrint("data/array-of-numbers.json") {
        Json.decodeFromString<List<Double>>(it)
    }
}

private fun showExample3() {
    FileUtil.readAndPrint("data/object-of-numbers.json") {
        Json.decodeFromString<Map<String, Double>>(it)
    }
}

private fun showExample4() {
    FileUtil.readAndPrint("data/comprehensive-object.json") {
        Json.decodeFromString<MySerializable>(it)
    }
}

private fun showExample5() {
    FileUtil.readAndPrint("data/comprehensive-array.json") {
        Json.decodeFromString<List<MySerializable2>>(it)
    }
}

private fun main() {
    showExample1()
    showExample2()
    showExample3()
    showExample4()
    showExample5()
}
