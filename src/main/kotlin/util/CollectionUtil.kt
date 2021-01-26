package util

object CollectionUtil {
    fun Collection<*>.splitLast() = with(chunked(size - 1)) { first() to last().last() }
    fun <T> addOrRemove(xs: Collection<T>?, x: T) = xs?.run { if (any { it == x }) minus(x) else plus(x) }
    fun Collection<*>.countOccurrences() = groupingBy { it }.eachCount()
    fun Collection<*>.hasDuplicates() = this.groupingBy { it }.eachCount().any { it.value > 1 }
    fun Collection<*>.rotateLeft(distance: Int): List<*> = drop(distance) + take(distance)
}
