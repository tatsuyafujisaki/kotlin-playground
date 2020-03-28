package sort

import utils.swap

object SelectionSort {
    fun sort(xs: IntArray) {
        for (i in xs.indices) {
            var indexOfMin = i
            for (j in i + 1 until xs.size) {
                if (xs[j] < xs[indexOfMin]) {
                    indexOfMin = j
                }
            }
            if (i != indexOfMin) {
                swap(xs, i, indexOfMin)
            }
        }
    }
}