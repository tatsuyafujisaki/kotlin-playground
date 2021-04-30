package util

import java.util.Collections

object ArrayUtil {
    object RotationUtil {
        /** @param distance must be less than or equal to the length of the array. */
        fun <T> Array<T>.rotateLeft(distance: Int) = drop(distance) + take(distance)

        /** @param distance must be less than or equal to the length of the array. */
        fun <T> Array<T>.rotateRight(distance: Int) = takeLast(distance) + dropLast(distance)

        fun <T> Array<T>.rotate(distance: Int) =
            toList().also { // toList() is a deep copy to avoid changing the original array.
                Collections.rotate(it, distance)
            }
    }

    val pow2 = intArrayOf(
        1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024,
        2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576,
        2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824
    )

    fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    /**
     * @receiver must not be empty.
     */
    fun IntArray.minMax(): Pair<Int, Int> {
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

    val IntArray.indexOfMin: Int
        get() {
            var indexOfMin = 0
            for (i in 1 until size) {
                if (this[i] < this[indexOfMin]) indexOfMin = i
            }
            return indexOfMin
        }

    val IntArray.indexOfMax: Int
        get() {
            var indexOfMax = 0
            for (i in 1 until size) {
                if (this[i] > this[indexOfMax]) indexOfMax = i
            }
            return indexOfMax
        }

    fun IntArray.median(): Double {
        val i = size / 2
        return if (size % 2 == 0) (this[i - 1] + this[i]) / 2.0 else this[i].toDouble()
    }

    fun IntArray.mode() = toList().groupingBy { it }.eachCount().maxByOrNull { it.value }?.key

    fun IntArray.medianByCountingSort(): Double {
        fun median(onMiddleIndex: (IntArray, Int) -> Double): Double {
            val counts = IntArray(maxOrNull()!! + 1)
            for (x in this) {
                counts[x]++
            }
            val indexOfMedian = size / 2
            var i = 0
            counts.forEachIndexed { j, x ->
                repeat(x) {
                    this[i] = j
                    if (i == indexOfMedian) return onMiddleIndex(this, i)
                    i++
                }
            }
            error("Unexpected to reach here.")
        }
        return if (size % 2 == 0) {
            median { xs, i -> (xs[i - 1] + xs[i]) / 2.0 }
        } else {
            median { xs, j -> xs[j].toDouble() }
        }
    }

    fun IntArray.permute(left: List<Int> = emptyList(), right: List<Int> = toList()): List<List<Int>> =
        if (right.isEmpty()) listOf(left) else right.flatMap { permute(left + it, right - it) }

    fun IntArray.permuteWithoutDuplicates() = permute().distinct()

    /** @return the number of the given element in a sorted array */
    fun LongArray.count(element: Long): Int {
        var i = binarySearch(element)
        if (i < 0) return 0
        var count = 1
        var j = i
        while (getOrNull(--i) == element) count++
        while (getOrNull(++j) == element) count++
        return count
    }
}
