package sort

import utils.swap

object Heapsort {
    fun sort(xs: IntArray) {
        for (i in xs.size / 2 downTo 0) {
            heapify(xs, xs.size, i)
        }

        for (i in xs.indices.last downTo 0) {
            swap(xs, 0, i)
            heapify(xs, i, 0)
        }
    }

    private fun heapify(xs: IntArray, n: Int, currentNodexIndex: Int) {
        var indexOfMax = currentNodexIndex
        val leftChildIndex = 2 * currentNodexIndex + 1
        val rightChildIndex = 2 * currentNodexIndex + 2
        if (leftChildIndex < n && xs[indexOfMax] < xs[leftChildIndex]) {
            indexOfMax = leftChildIndex
        }
        if (rightChildIndex < n && xs[indexOfMax] < xs[rightChildIndex]) {
            indexOfMax = rightChildIndex
        }
        if (indexOfMax != currentNodexIndex) {
            swap(xs, currentNodexIndex, indexOfMax)
            heapify(xs, n, indexOfMax)
        }
    }
}