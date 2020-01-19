package util

object IterableUtil {
    fun <T> tail(xs: Iterable<T>) = xs.drop(1)
    fun <T> addOrRemove(xs: Iterable<T>, x: T) = if (xs.any { it == x }) xs.minus(x) else xs.plus(x)
    fun <T> hasDuplicates(xs: Iterable<T>) = xs.groupingBy { it }.eachCount().any { it.value > 1 }
    fun toZeroBased(xs: Iterable<Int>) = xs.map { it - 1 }
    fun prefixSum(xs: Iterable<Int>) = xs.scan(0) { acc, x -> acc + x }

    /**
     * Refer to [ListUtil.rotateRight] for rotating right.
     *
     * @param distance must be less than or equal to the length of the list. Otherwise, the output list will be the same as the input list.
     */
    fun <T> rotateLeft(xs: Iterable<T>, distance: Int): List<T> = xs.drop(distance) + xs.take(distance)
}
