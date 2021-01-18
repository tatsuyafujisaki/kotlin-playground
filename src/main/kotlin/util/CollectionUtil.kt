package util

object CollectionUtil {
    fun <T> addOrRemove(xs: Collection<T>?, x: T) = xs?.run {
        if (any { it == x }) minus(x) else plus(x)
    }

    fun <T> Collection<T>.countOccurrences() = groupingBy { it }.eachCount()
    fun <T> Collection<T>.hasDuplicates() = this.groupingBy { it }.eachCount().any { it.value > 1 }
    fun <T> Collection<T>.rotateLeft(distance: Int): List<T> = drop(distance) + take(distance)
}
