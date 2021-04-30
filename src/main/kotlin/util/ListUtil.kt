package util

import java.util.Collections

object ListUtil {
    object RotationUtil {
        /** @param distance must be less than or equal to the length of the list. */
        fun <T> List<T>.rotateLeft(distance: Int) = drop(distance) + take(distance)

        /** @param distance must be less than or equal to the length of the list. */
        fun <T> List<T>.rotateRight(distance: Int) = takeLast(distance) + dropLast(distance)


        fun <T> List<T>.rotate(distance: Int) =
            toList().also { // toList() is a deep copy to avoid changing the original array.
                Collections.rotate(it, distance)
            }
    }

    fun <T> List<T>.splitLast() = with(chunked(lastIndex)) { first() to last().last() }
    fun List<CharArray>.deepCopy() = map { it.clone() }

    fun MutableList<Int>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    fun List<*>.toPair() = let {
        it[0] to it[1]
    }

    fun List<*>.pairs() = sequence {
        for (i in indices) {
            for (j in i + 1 until size) yield(this@pairs[i] to this@pairs[j])
        }
    }

    /**
     * @receiver must not be empty.
     */
    fun List<Int>.minMax(): Pair<Int, Int> {
        var min = this[0]
        var max = this[0]
        for (i in 1 until size) {
            if (this[i] < min) {
                min = this[i]
            } else if (this[i] > max) {
                max = this[i]
            }
        }
        return min to max
    }

    /**
     * @return the number of the given element in a sorted list
     */
    fun <T : Comparable<T>> List<T>.count(element: T): Int {
        var i = binarySearch(element)
        if (i < 0) return 0
        var count = 1
        var j = i
        while (getOrNull(--i) == element) count++
        while (getOrNull(++j) == element) count++
        return count
    }
}
