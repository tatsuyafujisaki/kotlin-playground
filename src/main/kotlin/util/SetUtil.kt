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