package sort

object BubbleSort {
    fun sort(xs: IntArray) {
        var n = xs.size
        while (1 < n) {
            var lastSwappedIndex = 0
            for (i in 0 until n - 1) {
                if (xs[i] > xs[i + 1]) {
                    xs.swap(i, i + 1)
                    lastSwappedIndex = i + 1
                }
            }
            n = lastSwappedIndex
        }
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}
