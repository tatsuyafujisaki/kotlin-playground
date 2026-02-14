package json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

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

private fun main() {
    val personAdapter = MoshiConverter.getAdapter<Person>()
    val person = Person(name = "Ava", age = 28)
    val personJson = personAdapter.toJson(person)
    println(personJson)
    val personDecoded = personAdapter.fromJson(personJson)
    println(personDecoded)

    val listAdapter = MoshiConverter.getListAdapter<Person>()
    val people = listOf(Person("Noah", 31), Person("Mia", 25))
    val peopleJson = listAdapter.toJson(people)
    println(peopleJson)
    val peopleDecoded = listAdapter.fromJson(peopleJson)
    println(peopleDecoded)

    val mapAdapter = MoshiConverter.getMapAdapter<Person>()
    val peopleById = mapOf("u1" to Person("Leo", 40), "u2" to Person("Zoe", 19))
    val mapJson = mapAdapter.toJson(peopleById)
    println(mapJson)
    val mapDecoded = mapAdapter.fromJson(mapJson)
    println(mapDecoded)
}
