package util

object CollectionUtil {
    fun <T> addOrRemove(xs: Collection<T>?, x: T) = xs?.run {
        if (any { it == x }) minus(x) else plus(x)
    }

    fun <T> countOccurrences(xs: Collection<T>) = xs.groupingBy { it }.eachCount()
}
