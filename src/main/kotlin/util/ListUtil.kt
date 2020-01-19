package util

import java.util.Collections

object ListUtil {
    /**
     * Refer to [IterableUtil.rotateLeft] for rotating left.
     *
     * @param distance must be less than or equal to the length of the list.
     */
    fun <T> rotateRight(xs: List<T>, distance: Int) = xs.takeLast(distance) + xs.dropLast(distance)

    fun <T> List<T>.rotate(distance: Int) =
        toList().also { // toList() is a deep copy to avoid changing the original array.
            Collections.rotate(it, distance)
        }

    fun <T> splitLast(xs: List<T>) = with(xs.chunked(xs.lastIndex)) { first() to last().last() }

    fun swap(xs: MutableList<Int>, i: Int, j: Int) {
        val temp = xs[i]
        xs[i] = xs[j]
        xs[j] = temp
    }

    fun <T> toPair(xs: List<T>) = xs[0] to xs[1]

    fun <T> pairs(xs: List<T>) = sequence {
        for (i in xs.indices) {
            for (j in i + 1 until xs.size) yield(xs[i] to xs[j])
        }
    }

    /**
     * @receiver must not be empty.
     */
    fun minMax(xs: List<Int>): Pair<Int, Int> {
        var min = xs[0]
        var max = xs[0]
        for (i in 1 until xs.size) {
            if (xs[i] < min) {
                min = xs[i]
            } else if (xs[i] > max) {
                max = xs[i]
            }
        }
        return min to max
    }

    /**
     * @return the number of the given element in a sorted list
     */
    fun <T : Comparable<T>> count(xs: List<T>, element: T): Int {
        var i = xs.binarySearch(element)
        if (i < 0) return 0
        var count = 1
        var j = i
        while (xs.getOrNull(--i) == element) count++
        while (xs.getOrNull(++j) == element) count++
        return count
    }
}
