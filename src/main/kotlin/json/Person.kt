package json

import kotlinx.serialization.Serializable

/**
 * "@Serializable" is required for Kotlin Serialization but not required for Gson and Moshi.
 *
 * @see [https://kotlinlang.org/docs/reference/serialization.html]
 */
@Serializable
data class Person(val name: String, val age: Int)
