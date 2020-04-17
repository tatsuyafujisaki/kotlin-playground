package samples

import converters.MoshiConverter

private data class Person(val name: String, val age: Int)

fun sample() {
    val person = Person("Taro", 20)
    val json = MoshiConverter.serialize(person)
    println(json)

    val person2: Person? = MoshiConverter.deserialize(json)
    println(person2)
}