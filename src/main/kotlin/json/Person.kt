package json

import kotlinx.serialization.Serializable

/**
 * Kotlin Serialization, not Moshi or Gson, requires “@Serializable”.
 *
 * @see <a href="https://kotlinlang.org/docs/reference/serialization.html">Serialization</a>
 */
@Serializable
data class Person(val name: String, val age: Int)
