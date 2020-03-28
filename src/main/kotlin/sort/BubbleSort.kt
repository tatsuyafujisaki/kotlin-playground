package sort

/**
 * For educational purposes only
 */
object BubbleSort {
    fun sort(xs: IntArray) {
        var n = xs.size
        do {
            var lastSwappedIndex = 0
            for (i in 0..(n - 2)) {
                if (xs[i + 1] < xs[i]) {
                    swap(xs, i, i + 1)
                    lastSwappedIndex = i + 1
                }
            }
            n = lastSwappedIndex
        } while (1 < n)
    }

    private fun swap(xs: IntArray, i: Int, j: Int) {
        val tmp = xs[i]
        xs[i] = xs[j]
        xs[j] = tmp
    }
}