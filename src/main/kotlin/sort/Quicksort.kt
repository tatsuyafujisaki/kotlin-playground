package sort

object Quicksort {
    fun sort(xs: IntArray) {
        sort(xs, 0, xs.lastIndex)
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
            while (xs[i] < pivot) i++
            while (pivot < xs[j]) j--
            if (j < i) break
            xs.swap(i++, j--)
        }
        return i
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}
