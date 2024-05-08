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

    println(Json.decodeFromString<Person>(json))
}

private fun showExample2() {
    // Array of numbers
    println(Json.decodeFromString<List<Double>>(string = "[-1, 3.14]"))
}

private fun showExample3() {
    // Object of numbers
    println(Json.decodeFromString<Map<String, Double>>(string = """{ "a": -1, "b": 3.14 }""".trimIndent()))
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
//    showExample4()
//    showExample5()
}
