package json

data class MySerializable(
    val object1: MySerializable2, val object2: MySerializable2
)

data class MySerializable2(
    val null1: String?,
    val boolean1: Boolean?,
    val boolean2: Boolean?,
    val string1: String?,
    val string2: String?,
    val array1: List<Double>?,
    val array2: List<Double>?
)
