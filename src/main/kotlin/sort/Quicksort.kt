package sort

import utils.swap

object Quicksort {
    fun sortRecursive(xs: IntArray): IntArray {
        if (xs.isEmpty()) {
            return xs
        }
        val pivot = xs[xs.size / 2]
        return sortRecursive(xs.filter { it < pivot }.toIntArray()) +
            xs.filter { it == pivot } +
            sortRecursive(xs.filter { it > pivot }.toIntArray())
    }

    fun sortImperative(xs: IntArray) {
        sort(xs, 0, xs.indices.last)
    }

    private fun sort(xs: IntArray, low: Int, high: Int) {
        if (low < high) {
            val p = partition(xs, low, high)
            sort(xs, low, p - 1)
            sort(xs, p, high)
        }
    }

    private fun partition(xs: IntArray, low: Int, high: Int): Int {
        var i = low
        var j = high
        val pivot = xs[(i + j) / 2]

        while (true) {
            while (xs[i] < pivot) {
                i++
            }

            while (pivot < xs[j]) {
                j--
            }

            if (j < i) {
                break
            }

            swap(xs, i++, j--)
        }

        return i
    }
}