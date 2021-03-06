package util

object IterableUtil {
    val Iterable<*>.head get() = first()
    val Iterable<*>.tail get() = drop(1)
    fun <T> Iterable<T>.addOrRemove(x: T) = run { if (any { it == x }) minus(x) else plus(x) }
    fun Iterable<*>.hasDuplicates() = groupingBy { it }.eachCount().any { it.value > 1 }
    fun Iterable<*>.rotateLeft(distance: Int): List<*> = drop(distance) + take(distance)
    fun Iterable<Int>.toZeroBased() = map { it - 1 }
    fun Iterable<Int>.prefixSum() = scan(0) { acc, x -> acc + x }

    /** For a competitive programming platform that does not support scan() of Kotlin 1.4 */
    fun <T, R> Iterable<T>.myScan(
        initial: R,
        operation: (acc: R, T) -> R
    ): List<R> {
        val result = mutableListOf(initial)
        forEachIndexed { i, x ->
            result.add(operation(result[i], x))
        }
        return result
    }

    /** For a competitive programming platform that does not support runningReduce() of Kotlin 1.4 */
    fun <S, T : S> Iterable<T>.myRunningReduce(operation: (acc: S, T) -> S): List<S> {
        val iterator = iterator()
        if (!iterator.hasNext()) return emptyList()
        var acc: S = iterator.next()
        val result = mutableListOf(acc)
        while (iterator.hasNext()) {
            acc = operation(acc, iterator.next())
            result.add(acc)
        }
        return result
    }
}
