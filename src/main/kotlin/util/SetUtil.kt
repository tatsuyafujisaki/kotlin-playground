package util

object SetUtil {
    fun <E> addOrRemove1(set: Set<E>, element: E) = if (element in set) {
        set - element
    } else {
        set + element
    }

    fun <E> addOrRemove2(set: Set<E>, element: E) = set.toMutableSet().apply {
        add(element) || remove(element)
    }.toSet()
}

private fun main() {
    println(sortedSetOf("10", "1", "2")) // [1, 10, 2]
    println(sortedSetOf(compareBy { it.toInt() }, "10", "1", "2")) // [1, 2, 10]

    val set = setOf("10", "1", "2")
    println(set.toSortedSet()) // [1, 10, 2]
    println(set.toSortedSet(compareBy { it.toInt() })) // [1, 2, 10]
}
