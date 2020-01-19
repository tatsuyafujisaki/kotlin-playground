package sort

object BubbleSort {
    fun sort(xs: IntArray) {
        var n = xs.size
        while (1 < n) {
            var lastSwappedIndex = 0
            repeat(n - 1) {
                if (xs[it] > xs[it + 1]) {
                    xs.swap(it, it + 1)
                    lastSwappedIndex = it + 1
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
