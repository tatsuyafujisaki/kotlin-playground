package util

import java.util.Collections

object ListUtil {
    fun List<*>.splitLast() = with(chunked(lastIndex)) { first() to last().last() }
    fun List<*>.rotateRight(distance: Int): List<*> = takeLast(distance) + dropLast(distance)

    fun MutableList<Int>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    fun List<*>.rotate(distance: Int): List<*> {
        with(toList()) {
            Collections.rotate(this, distance)
            return this
        }
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
        val i = binarySearch(element)
        if (i < 0) return 0
        var j = i
        var k = i
        var count = 1
        while (j-- > 0 && this[j] == element) count++
        while (k++ < lastIndex && this[k] == element) count++
        return count
    }
}
