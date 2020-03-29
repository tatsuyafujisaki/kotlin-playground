package sort

import utils.swap

object BubbleSort {
    fun sort(xs: IntArray) {
        var n = xs.size
        while (1 < n) {
            var lastSwappedIndex = 0
            for (i in 0..(n - 2)) {
                if (xs[i + 1] < xs[i]) {
                    swap(xs, i, i + 1)
                    lastSwappedIndex = i + 1
                }
            }
            n = lastSwappedIndex
        }
    }
}