package sort

object SelectionSort {
    fun sort(xs: IntArray) {
        for (i in xs.indices) {
            var indexOfMin = i
            for (j in i + 1 until xs.size) {
                if (xs[j] < xs[indexOfMin]) indexOfMin = j
            }
            if (i != indexOfMin) xs.swap(i, indexOfMin)
        }
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}
