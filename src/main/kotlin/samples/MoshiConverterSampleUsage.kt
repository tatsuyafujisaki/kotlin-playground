package samples

import converters.GsonJsonConverter
import converters.MoshiJsonConverter

private data class Person(val name: String, val age: Int)

fun gsonUsageSample() {
    val person = Person("Taro", 20)
    val json = GsonJsonConverter.serialize(person)
    println(json)

    val person2: Person = GsonJsonConverter.deserialize(json)
    println(person2)
}

fun moshiUsageSample() {
    val person = Person("Taro", 20)
    val json = MoshiJsonConverter.serialize(person)
    println(json)

    val person2: Person? = MoshiJsonConverter.deserialize(json)
    println(person2)
}