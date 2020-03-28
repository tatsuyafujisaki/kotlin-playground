package sort

object InsertionSort {
    fun sort(xs: IntArray) {
        for (i in 1 until xs.size) {
            val x = xs[i]
            var j = i - 1
            while (0 <= j && x < xs[j]) {
                xs[j + 1] = xs[j]
                j--
            }
            xs[j + 1] = x
        }
    }
}