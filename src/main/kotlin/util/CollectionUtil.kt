package util

import util.CollectionUtil.isNeitherNullNorEmpty

object CollectionUtil {
    fun <T> Collection<T>?.isNeitherNullNorEmpty() = !isNullOrEmpty()

    fun <T> Collection<T>.permute(): List<List<T>> {
        fun <T> Collection<T>.permute(result: List<T> = emptyList()): List<List<T>> = if (isEmpty()) {
            listOf(result)
        } else {
            flatMap {
                (this - it).permute(result + it)
            }
        }
        return permute()
    }

    fun <T> Collection<T>.permuteWithoutDuplicates() = permute().distinct()
}

private fun main() {
    val xs: List<String>? = listOf("a")
    val ys: List<String>? = emptyList()
    val zs: List<String>? = null

    println(xs.isNeitherNullNorEmpty()) // true
    println(ys.isNeitherNullNorEmpty()) // false
    println(zs.isNeitherNullNorEmpty()) // false
}
