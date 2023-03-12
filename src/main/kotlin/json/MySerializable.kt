package json

import kotlinx.serialization.Serializable

/**
 * Kotlin Serialization requires “@Serializable”.
 *
 * @see <a href="https://kotlinlang.org/docs/reference/serialization.html">Serialization</a>
 */
@Serializable
data class MySerializable(
    val object1: MySerializable2, val object2: MySerializable2
)

@Serializable
data class MySerializable2(
    val null1: String?,
    val boolean1: Boolean?,
    val boolean2: Boolean?,
    val number1: Double?,
    val number2: Double?,
    val string1: String?,
    val string2: String?,
    val array1: List<Double>?,
    val array2: List<Double>?
)
