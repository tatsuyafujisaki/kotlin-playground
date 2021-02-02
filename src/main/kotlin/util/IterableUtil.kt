package util

object IterableUtil {
    fun <T> addOrRemove(xs: Iterable<T>?, x: T) = xs?.run { if (any { it == x }) minus(x) else plus(x) }
    fun Iterable<*>.counts() = groupingBy { it }.eachCount()
    fun Iterable<*>.hasDuplicates() = this.groupingBy { it }.eachCount().any { it.value > 1 }
    fun Iterable<*>.rotateLeft(distance: Int): List<*> = drop(distance) + take(distance)
    fun Iterable<Int>.prefixSum() = scan(0) { acc, x -> acc + x }
}
